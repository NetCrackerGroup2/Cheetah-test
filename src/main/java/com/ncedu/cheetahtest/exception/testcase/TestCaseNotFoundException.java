package com.ncedu.cheetahtest.exception.testcase;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Test Case Not Found")
public class TestCaseNotFoundException extends RuntimeException{
}
