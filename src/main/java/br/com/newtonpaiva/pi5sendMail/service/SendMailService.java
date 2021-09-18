package br.com.newtonpaiva.pi5sendMail.service;

import br.com.newtonpaiva.pi5sendMail.config.EmailCfg;
import br.com.newtonpaiva.pi5sendMail.dto.FeedbackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Service
public class SendMailService {

    @Autowired
    private EmailCfg emailCfg;

    public void sendMail(FeedbackDTO feedbackDTO) throws MessagingException, IOException {

        String fromEmail = emailCfg.getUsername();
        String toEmail = feedbackDTO.getEmail();

        Properties properties = new Properties();

        properties.put("mail.smtp.host", emailCfg.getHost());
        properties.put("mail.smtp.port", emailCfg.getPort());
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", emailCfg.getPort());
        properties.put("mail.smtp.ssl.enable", "true");


        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailCfg.getUsername(), emailCfg.getPassword());
                    }
                });

        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(toEmail));
        message.setSubject("Arquivo do Projeto Integrador");

        Multipart emailContent = new MimeMultipart();

        File file = new File(feedbackDTO.getPath());

        MimeBodyPart fileAttachment = new MimeBodyPart();
        fileAttachment.attachFile(file.getAbsolutePath());
        emailContent.addBodyPart(fileAttachment);
        
        message.setContent(emailContent);

        Transport.send(message);

    }

}
