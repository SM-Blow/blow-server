package com.blow.server.api.common.exception;

import org.springframework.http.HttpStatus;


public class AuthException extends RentalException {
    public AuthException(String message, HttpStatus statusCode) {
        super(message, statusCode);
    }
}
