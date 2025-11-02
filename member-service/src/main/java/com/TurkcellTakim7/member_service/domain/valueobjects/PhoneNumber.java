package com.TurkcellTakim7.member_service.domain.valueobjects;

import java.util.Objects;
import java.util.regex.Pattern;

public record PhoneNumber(String value) {


    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\+?[0-9]{10,15}$");

    public PhoneNumber {
        Objects.requireNonNull(value, "Phone number cannot be null");
        if (!PHONE_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid phone number format: " + value);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}