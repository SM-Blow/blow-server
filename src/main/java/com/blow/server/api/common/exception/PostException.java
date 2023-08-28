package com.blow.server.api.common.exception;

import org.springframework.http.HttpStatus;

public class PostException extends RentalException{
    public PostException(String message, HttpStatus statusCode){
        super(message, statusCode);
    }
}
