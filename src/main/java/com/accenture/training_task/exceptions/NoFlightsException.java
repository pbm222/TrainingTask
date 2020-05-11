package com.accenture.training_task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NoFlightsException extends RuntimeException{

    public NoFlightsException(String message) {
        super(message);
    }

    public NoFlightsException(String message, Throwable cause) {
        super(message, cause);
    }
}
