package com.ncedu.cheetahtest.service.mail;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Component
public class HtmlMailImpl implements HtmlMail {

  public Optional<String> getHtmlWithStringInside(String message, String htmlPath) {
    File file = new File(htmlPath);

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
