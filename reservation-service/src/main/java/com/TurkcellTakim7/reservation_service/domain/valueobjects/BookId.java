package com.TurkcellTakim7.reservation_service.domain.valueobjects;

import java.io.Serializable;
import java.util.Objects;

/**
 * Diğer mikroservisteki Book/BookItem'ın kimliğini temsil eder.
 */
public record BookId(String value) implements Serializable {

    public BookId {
        Objects.requireNonNull(value, "BookId value cannot be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException("BookId value cannot be blank");
        }
        // İstersen:
        // UUID.fromString(value);
    }

    public static BookId of(String raw) {
        return new BookId(raw);
    }

    @Override
    public String toString() {
        return value;
    }
}
