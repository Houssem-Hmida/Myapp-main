package com.example.myapp.presentation.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailUtils {

    @Autowired
    private JavaMailSender emailSender;

    public void forgotMail(String to,String subject,String username,String password ) throws MessagingException{
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom("ymahfoudh55@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        String htmlMsg = "<p><b>Your Login details for ST2i Management System</b><br><b>Username: </b> " + username + " <br><b>Password: </b> " + password + "<br><a href=\"http://localhost:4200/login\">Click here to login</a></p>";
        message.setContent(htmlMsg,"text/html");
        emailSender.send(message);
    }
}
