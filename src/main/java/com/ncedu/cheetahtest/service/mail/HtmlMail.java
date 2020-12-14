package com.ncedu.cheetahtest.service.mail;

import java.util.List;
import java.util.Optional;

public interface HtmlMail {

    Optional<String> getHtmlWithStringInside(String message, String htmlPath);

    Optional<String> getHtmlWithStrings(List<String> messages, String htmlPath);
}
