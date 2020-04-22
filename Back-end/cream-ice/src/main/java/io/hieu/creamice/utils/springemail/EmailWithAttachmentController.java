package io.hieu.creamice.utils.springemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Controller
public class EmailWithAttachmentController {
    @Autowired
    public JavaMailSender emailSender;

    @ResponseBody
    @RequestMapping("/send-email-with-attechment")
    public String sendAttachmentEmail() throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart);

        helper.setTo(SpringEmailConstants.RECEIVER_EMAIL);
        helper.setSubject("Testing Email.");

        helper.setText("Hello! This is a testing email. Please ignore this email. \nThank you and have a good day!");

        String path1 = "/D:/hieu/Downloads/demo.txt";
        String path2 = "/D:/hieu/Downloads/demo.zip";

        // Attachment 1
        FileSystemResource file1 = new FileSystemResource(new File(path1));
        helper.addAttachment("Text file.", file1);

        // Attachment 2
        FileSystemResource file2 = new FileSystemResource(new File(path2));
        helper.addAttachment("Zip file", file2);

        emailSender.send(message);

        return "Email Sent!";
    }
}