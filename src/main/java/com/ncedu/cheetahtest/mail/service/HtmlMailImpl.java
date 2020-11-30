package com.ncedu.cheetahtest.mail.service;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Optional;

@Component
public class HtmlMailImpl implements HtmlMail{

    private static final String HTML_PATH = "src/main/resources/mail/email.html";

    public Optional<String> getHtmlWithStringInside(String message) {
        File file = new File(HTML_PATH);

        StringBuilder htmlStringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            while (reader.ready()) {
                htmlStringBuilder.append(reader.readLine());
            }
            return Optional.of(String.format(htmlStringBuilder.toString(), message));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
