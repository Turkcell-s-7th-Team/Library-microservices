package com.TurkcellTakim7.loan_service.domain.valueobjects;

import java.io.Serializable;
import java.util.UUID;

public record StaffId(UUID value) implements Serializable {

    public StaffId {
        if (value == null) {
            throw new IllegalArgumentException("StaffId cannot be null");
        }
    }

    public static StaffId fromString(String raw) {
        return new StaffId(UUID.fromString(raw));
    }
}
