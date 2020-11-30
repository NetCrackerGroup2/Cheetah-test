package com.ncedu.cheetahtest.exception.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Invalid email or password")
public class BadCredentialsException extends RuntimeException{
}
