package com.intelligentcat.parkwithyoubackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidAccountException extends RuntimeException {
    public InvalidAccountException() {
        super("The user does not exist.");
    }
}
