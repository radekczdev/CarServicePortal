package com.czajor.carserviceportal.service;

import com.czajor.carserviceportal.model.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public void run(final Mail mail) {
        LOGGER.info("Preparing to send email...");
        try {
            mailSender.send(createMail(mail));
            LOGGER.info("Email successfully sent.");
        } catch (MailException e) {
            LOGGER.error("Sending email thrown exception: " + e.getMessage() + e);
        }
    }

    private SimpleMailMessage createMail(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mail.getFrom());
        mailMessage.setTo(mail.getTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getBody());
        return mailMessage;
    }
}
