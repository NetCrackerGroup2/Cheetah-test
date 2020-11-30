package com.ncedu.cheetahtest.service.mail;

public interface EmailService {
    void sendSimpleMessage(String to, String text, String subject);

    void sendMessageWithAttachment(String to, String text, String subject, String htmlPath);
}
