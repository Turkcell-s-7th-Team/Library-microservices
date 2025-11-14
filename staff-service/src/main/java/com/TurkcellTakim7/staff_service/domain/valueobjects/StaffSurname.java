package com.TurkcellTakim7.staff_service.domain.valueobjects;

import java.util.Objects;

public record StaffSurname(String value) {
    public StaffSurname {
        Objects.requireNonNull(value, "Staff surname cannot be null");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Staff surname cannot be empty");
        }
        if (trimmed.length() > 100) {
            throw new IllegalArgumentException("Staff surname cannot exceed 100 characters");
        }
        value = trimmed;
    }

    @Override
    public String toString() {
        return value;
    }
}
