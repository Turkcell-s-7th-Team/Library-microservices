package com.TurkcellTakim7.member_service.domain.exceptions;

import com.TurkcellTakim7.member_service.domain.valueobjects.Email;

public class EmailAlreadyExistsException extends RuntimeException {

    private final Email email;

    public EmailAlreadyExistsException(Email email) {
        super("Email already exists: " + email.value());
        this.email = email;
    }

    public EmailAlreadyExistsException(String message, Email email) {
        super(message);
        this.email = email;
    }

    public Email getEmail() {
        return email;
    }
}

