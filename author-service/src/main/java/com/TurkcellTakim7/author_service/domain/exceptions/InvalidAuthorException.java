package com.TurkcellTakim7.author_service.domain.exceptions;

public class InvalidAuthorException extends DomainException {

    public InvalidAuthorException(String message) {
        super(message);
    }

    public InvalidAuthorException(String message, Throwable cause) {
        super(message, cause);
    }
}
