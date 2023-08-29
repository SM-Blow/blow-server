package com.blow.server.api.common.exception;

import org.springframework.http.HttpStatus;

public class TokenException extends RentalException {

    public TokenException(String message, HttpStatus statusCode) {
        super(message, statusCode);
    }

}
