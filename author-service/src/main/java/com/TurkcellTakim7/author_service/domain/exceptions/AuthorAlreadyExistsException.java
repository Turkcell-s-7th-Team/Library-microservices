package com.TurkcellTakim7.author_service.domain.exceptions;

import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorEmail;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorPhoneNumber;

public class AuthorAlreadyExistsException extends DomainException {

    public AuthorAlreadyExistsException(AuthorEmail email) {
        super("Author already exists with email: " + email.email());
    }

    public AuthorAlreadyExistsException(AuthorPhoneNumber phoneNumber) {
        super("Author already exists with phone number: " + phoneNumber.phoneNumber());
    }

    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
}
