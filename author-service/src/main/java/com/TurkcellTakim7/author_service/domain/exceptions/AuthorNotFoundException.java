package com.TurkcellTakim7.author_service.domain.exceptions;

import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorId;

public class AuthorNotFoundException extends DomainException {

    public AuthorNotFoundException(AuthorId authorId) {
        super("Author not found with id: " + authorId.value());
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }
}
