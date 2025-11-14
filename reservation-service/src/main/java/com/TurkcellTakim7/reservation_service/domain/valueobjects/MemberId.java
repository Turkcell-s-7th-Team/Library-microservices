package com.TurkcellTakim7.reservation_service.domain.valueobjects;

import java.io.Serializable;
import java.util.Objects;

/**
 * Diğer mikroservisteki Member'ın kimliğini temsil eder.
 * Dış dünyadan String gelir, domain içinde VO olarak tutulur.
 */
public record MemberId(String value) implements Serializable {

    public MemberId {
        Objects.requireNonNull(value, "MemberId value cannot be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException("MemberId value cannot be blank");
        }
        // İstersen:
        // UUID.fromString(value); // format kontrolü için
    }

    public static MemberId of(String raw) {
        return new MemberId(raw);
    }

    @Override
    public String toString() {
        return value;
    }
}
