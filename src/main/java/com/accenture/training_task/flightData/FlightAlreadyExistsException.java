package com.accenture.training_task.flightData;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class FlightAlreadyExistsException extends RuntimeException {
	public FlightAlreadyExistsException(String message) {
		super(message);
	}
}
