package com.ncedu.cheetahtest.exception.managelibraries;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Dont have enough rights to perform operation")
public class RightsPermissionException extends RuntimeException{
}
