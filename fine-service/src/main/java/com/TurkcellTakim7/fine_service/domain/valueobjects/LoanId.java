package com.TurkcellTakim7.fine_service.domain.valueobjects;

import java.util.UUID;

public record LoanId(UUID value) {
    public LoanId {
        if (value == null) {
            throw new IllegalArgumentException("LoanId cannot be null!");
        }
    }

    public static LoanId from(UUID id) {
        return new LoanId(id);
    }
}

