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
            helper.setSubject("Mã xác thực của bạn");
            helper.setText("DGửi User,\n\n"
                    + "Vui lòng sử dụng mã xác thực để hoàn tất thủ tục đăng ký tài khoản trên DoubleT:\n\n"
                    + token + "\n\n"
                    + "Mã xác thực chỉ có hạn trong vòng 12 giờ.\n\n"
                    + "Trân trọng,\nDoubleT Cinema Booking Online");

            mailSender.send(mimeMessage);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
