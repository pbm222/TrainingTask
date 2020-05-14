package com.accenture.training_task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightDoesNotExistException extends RuntimeException {
	public FlightDoesNotExistException(String message) {
		super(message);
	}
}
