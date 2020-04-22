package io.hieu.creamice.utils.springemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {
    @Autowired
    public JavaMailSender emailSender;

    @ResponseBody
    @RequestMapping("/send-normal-email")
    public String sendSimpleEmail() {
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(SpringEmailConstants.RECEIVER_EMAIL);
        message.setSubject("Testing Email.");
        message.setText("Hello! This is a testing email. Please ignore this email. \nThank you and have a good day!");

        // Send Message!
        this.emailSender.send(message);

        return "Email Sent!";
    }
}