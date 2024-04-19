package com.challege.backend_jr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TrainingPerformedNotFoundException extends RuntimeException {
    public TrainingPerformedNotFoundException(String message) {
        super(message);
    }
}
