package com.ncedu.cheetahtest.exception.managelibraries;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "unproper values in request body")
public class UnproperInputException extends RuntimeException{
}
