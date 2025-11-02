package com.TurkcellTakim7.member_service.domain.exceptions;

public class MemberValidationException extends RuntimeException {

    public MemberValidationException(String message) {
        super(message);
    }

    public MemberValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}

