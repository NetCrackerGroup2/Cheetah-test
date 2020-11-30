package com.ncedu.cheetahtest.service.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.util.Objects;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService{

    public static final String NET_CRACKER_USERNAME = "spring.mail.username";

    private final JavaMailSender emailSender;
    private final HtmlMail htmlMail;
    private final Environment environment;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, HtmlMail htmlMail, Environment environment) {
        this.emailSender = emailSender;
        this.htmlMail = htmlMail;
        this.environment = environment;
    }

    @Override
    public void sendSimpleMessage(String to, String text, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(Objects.requireNonNull(environment.getProperty(NET_CRACKER_USERNAME)));
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }

    @Override
    public void sendMessageWithAttachment(String to, String text, String subject, String htmlPath) {

        MimeMessage message = emailSender.createMimeMessage();
        try {

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(Objects.requireNonNull(environment.getProperty(NET_CRACKER_USERNAME)));
            helper.setTo(to);
            helper.setSubject(subject);

            String htmlString = htmlMail.getHtmlWithStringInside(text, htmlPath)
                    .orElseThrow(FileNotFoundException::new);

            helper.setText("", htmlString);
            emailSender.send(message);

            log.info("Email has been sent to " + to);

        } catch (MessagingException e) {
            log.error(String.format("Couldn't send message: %s", e.getMessage()));
        } catch (FileNotFoundException e) {
            log.error(String.format("Couldn't find html file by given path: %s", e.getMessage()));
        }
    }
}
