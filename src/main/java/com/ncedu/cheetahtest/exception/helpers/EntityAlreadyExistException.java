package com.ncedu.cheetahtest.exception.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Entity with such signature already exists")
public class EntityAlreadyExistException extends RuntimeException {
}
