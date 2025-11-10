package com.TurkcellTakim7.author_service.domain.exceptions;

public class InvalidPhoneNumberException extends DomainException {

    public InvalidPhoneNumberException(String phoneNumber) {
        super("Invalid phone number format: " + phoneNumber);
    }

    public InvalidPhoneNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
