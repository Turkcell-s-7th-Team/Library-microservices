package com.TurkcellTakim7.loan_service.domain.valueobjects;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record LoanId(UUID value) implements Serializable {

    public LoanId {
        Objects.requireNonNull(value, "Value for LoanId cannot be null");
    }

    public static LoanId generateLoanId() {
        return new LoanId(UUID.randomUUID());
    }

}
