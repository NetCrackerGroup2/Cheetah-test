package com.ncedu.cheetahtest.exception.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "JWT token is expired or invalid")
public class JwtAuthenticationException extends RuntimeException{
}
