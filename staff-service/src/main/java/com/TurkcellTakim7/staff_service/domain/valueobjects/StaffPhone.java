package com.TurkcellTakim7.staff_service.domain.valueobjects;

import java.util.Objects;
import java.util.regex.Pattern;

public record StaffPhone(String value) {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{10,15}$");

    public StaffPhone {
        Objects.requireNonNull(value, "Staff phone cannot be null");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Staff phone cannot be empty");
        }
        if (!PHONE_PATTERN.matcher(trimmed).matches()) {
            throw new IllegalArgumentException("Invalid phone number format: " + trimmed);
        }
        value = trimmed;
    }

    @Override
    public String toString() {
        return value;
    }
}
