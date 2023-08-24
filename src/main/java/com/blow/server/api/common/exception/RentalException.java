package com.blow.server.api.common.exception;

import org.springframework.http.HttpStatus;

public class RentalException extends RuntimeException{
    private final HttpStatus statusCode;

    public RentalException(String message, HttpStatus statusCode){
        super(message);
        this.statusCode = statusCode;
    }
}
