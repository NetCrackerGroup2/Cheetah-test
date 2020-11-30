package com.ncedu.cheetahtest.service.mail;

import java.util.Optional;

public interface HtmlMail {
    Optional<String> getHtmlWithStringInside(String message, String htmlPath);
}
