package com.intelligentcat.parkwithyoubackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class NoAvailablePlaceException extends RuntimeException {
    public NoAvailablePlaceException() {
        super("No available parking place.");
    }
}
