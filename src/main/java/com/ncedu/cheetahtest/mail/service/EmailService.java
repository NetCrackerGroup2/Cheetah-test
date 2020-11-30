package com.ncedu.cheetahtest.mail.service;

public interface EmailService {
    void sendSimpleMessage(String to, String text, String subject);

    void sendMessageWithAttachment(String to, String text, String subject);
}
