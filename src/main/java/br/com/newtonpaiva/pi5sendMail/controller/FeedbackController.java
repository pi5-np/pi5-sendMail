package br.com.newtonpaiva.pi5sendMail.controller;

import br.com.newtonpaiva.pi5sendMail.dto.FeedbackDTO;
import br.com.newtonpaiva.pi5sendMail.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/send-mail")
public class FeedbackController {

    @Autowired
    public SendMailService sendMailService;

    @PostMapping
    public void sendFeedback(@RequestBody FeedbackDTO feedbackDTO) throws Exception {

        sendMailService.sendMail(feedbackDTO);

    }
}
