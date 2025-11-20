package com.TurkcellTakim7.fine_service.domain.valueobjects;

import java.math.BigDecimal;
import java.util.Objects;

public record Amount(BigDecimal value) {

    public Amount {
        Objects.requireNonNull(value, "Amount cannot be null");

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }
}
