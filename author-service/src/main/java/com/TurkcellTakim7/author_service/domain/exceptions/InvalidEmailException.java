package com.TurkcellTakim7.author_service.domain.exceptions;

public class InvalidEmailException extends DomainException {

    public InvalidEmailException(String email) {
        super("Invalid email format: " + email);
    }

    public InvalidEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
