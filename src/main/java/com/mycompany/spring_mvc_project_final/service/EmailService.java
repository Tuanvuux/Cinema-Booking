package com.mycompany.spring_mvc_project_final.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendTokenEmail(String recipientEmail, String token, String appUrl) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setTo(recipientEmail);
            helper.setSubject("Your Registration Token");
            helper.setText("Dear User,\n\n"
                    + "Please use the following token to complete your registration:\n\n"
                    + token + "\n\n"
                    + "This token is valid for 12 hours.\n\n"
                    + "Best regards,\nYour Application Team");

            mailSender.send(mimeMessage);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
