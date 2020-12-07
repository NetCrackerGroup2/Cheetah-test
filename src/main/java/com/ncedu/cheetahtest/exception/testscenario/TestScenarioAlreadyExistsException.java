package com.ncedu.cheetahtest.exception.testscenario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Test Scenario Already Exists")
public class TestScenarioAlreadyExistsException extends RuntimeException{
}
