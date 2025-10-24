package com.TurkcellTakim7.member_service.domain.valueobjects;

import java.util.Objects;

public record Address(String value) {

    public Address {
        Objects.requireNonNull(value, "Address cannot be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException("Address cannot be blank");
        }
        if (value.length() < 5) {
            throw new IllegalArgumentException("Address is too short");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}