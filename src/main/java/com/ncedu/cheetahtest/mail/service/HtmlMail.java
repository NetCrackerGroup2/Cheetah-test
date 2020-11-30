package com.ncedu.cheetahtest.mail.service;

import java.util.Optional;

public interface HtmlMail {
    Optional<String> getHtmlWithStringInside(String message);
}
