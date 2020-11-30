package com.ncedu.cheetahtest.exception.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Session timed out")
public class SessionTimeoutException extends RuntimeException{
}
