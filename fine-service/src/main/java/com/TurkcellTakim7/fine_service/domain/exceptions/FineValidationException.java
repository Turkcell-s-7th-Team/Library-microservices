package com.TurkcellTakim7.fine_service.domain.exceptions;

public class FineValidationException extends RuntimeException {

    public FineValidationException(String message) {
        super(message);
    }

    public FineValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
