package com.org.controller;

import com.org.managementservice.service.*;
import com.org.service.*;
import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public  ResponseEntity<ExceptionResponse> handleBookException(BookException exception){
        return new ResponseEntity<>(errorResponseBuilder(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public  ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException exception){
        return new ResponseEntity<>(errorResponseBuilder(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private static ExceptionResponse errorResponseBuilder(String message) {
        return ExceptionResponse.builder()
                .message(message)
                .timeStamp(LocalDateTime.now())
                .status(Boolean.FALSE)
                .build();
    }
}
