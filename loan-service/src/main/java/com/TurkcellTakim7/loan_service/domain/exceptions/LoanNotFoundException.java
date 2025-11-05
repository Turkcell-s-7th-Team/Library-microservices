package com.TurkcellTakim7.loan_service.domain.exceptions;

import java.util.UUID;

public class LoanNotFoundException extends DomainException {

    public LoanNotFoundException(UUID id) {
        super("Loan not found with id: " + id);
    }

    public LoanNotFoundException(String message) {
        super(message);
    }
}
