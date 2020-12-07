package com.ncedu.cheetahtest.exception.testscenario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Test Scenario Not Found")
public class TestScenarioNotFoundException extends RuntimeException{
}
