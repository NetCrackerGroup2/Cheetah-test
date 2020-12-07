package com.ncedu.cheetahtest.exception.testcase;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Test Case Already Exists")
public class TestCaseAlreadyExistsException extends RuntimeException{
}
