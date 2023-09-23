package com.blow.server.api.common.exception;

import org.springframework.http.HttpStatus;

public class ReportException extends RentalException{
    public ReportException(String message, HttpStatus statusCode) {
        super(message, statusCode);
    }
}
