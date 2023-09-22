package com.blow.server.api.common.exception;

import org.springframework.http.HttpStatus;

public class EventException extends RentalException{

    public EventException(String message, HttpStatus statusCode) {
        super(message, statusCode);
    }
}
