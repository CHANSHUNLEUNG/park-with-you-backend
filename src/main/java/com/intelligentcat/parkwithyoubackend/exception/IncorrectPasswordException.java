package com.intelligentcat.parkwithyoubackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException(){
        super("Password incorrect.");
    }
}
