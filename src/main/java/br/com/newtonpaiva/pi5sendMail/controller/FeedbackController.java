package br.com.newtonpaiva.pi5sendMail.controller;

import br.com.newtonpaiva.pi5sendMail.dto.FeedbackDTO;
import br.com.newtonpaiva.pi5sendMail.service.SendMailService;
import br.com.newtonpaiva.pi5sendMail.service.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/send-mail")
public class FeedbackController {

    @Autowired
    public SendMailService sendMailService;

    @Autowired
    private VerifyToken verifyToken;

    @PostMapping
    public ResponseEntity<String> sendFeedback(@RequestBody FeedbackDTO feedbackDTO, HttpServletRequest request) throws Exception {

        try {
            String bearer = verifyToken.findToken(request);
            if(!bearer.equals("")){
                verifyToken.validate(bearer);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is not found!");
            }

            try {
                sendMailService.sendMail(feedbackDTO);
            } catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }

            return ResponseEntity.ok().body("Okay");

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }
}
