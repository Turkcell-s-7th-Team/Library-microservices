package com.TurkcellTakim7.staff_service.domain.exceptions;

public class StaffValidationException extends RuntimeException {
    public StaffValidationException(String message) {
        super(message);
    }

    public StaffValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
