package com.TurkcellTakim7.author_service.domain.exceptions;

public class AuthorValidationException extends DomainException {

    public AuthorValidationException(String fieldName, String reason) {
        super(String.format("Author validation failed for field '%s': %s", fieldName, reason));
    }

    public AuthorValidationException(String message) {
        super(message);
    }
}
