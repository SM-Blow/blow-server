package com.blow.server.api.common;

import com.blow.server.api.common.exception.TokenException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.blow.server.api.common.message.ExceptionMessage.EMPTY_METHOD_ARGUMENT;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse> handleEntityNotFoundException(EntityNotFoundException exception){
        ApiResponse response = ApiResponse.fail(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        ApiResponse response = ApiResponse.fail(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String, Object> body = new HashMap<>();

        body.put("status", false);
        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("messages", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ApiResponse> handleTokenException(TokenException exception){
        ApiResponse response = ApiResponse.fail(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}