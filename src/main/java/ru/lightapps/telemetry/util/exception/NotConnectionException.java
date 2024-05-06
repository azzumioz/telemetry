package ru.lightapps.telemetry.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Not Connection")
public class NotConnectionException extends RuntimeException {
    public NotConnectionException(String message) {
        super(message);
    }
}
