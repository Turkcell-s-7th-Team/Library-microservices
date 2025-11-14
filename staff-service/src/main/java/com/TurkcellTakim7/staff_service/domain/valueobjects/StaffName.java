package com.TurkcellTakim7.staff_service.domain.valueobjects;

import java.util.Objects;

public record StaffName(String value) {
    public StaffName {
        Objects.requireNonNull(value, "Staff name cannot be null");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Staff name cannot be empty");
        }
        if (trimmed.length() > 100) {
            throw new IllegalArgumentException("Staff name cannot exceed 100 characters");
        }
        value = trimmed;
    }

    @Override
    public String toString() {
        return value;
    }
}
