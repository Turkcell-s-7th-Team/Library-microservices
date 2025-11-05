package com.TurkcellTakim7.loan_service.domain.valueobjects;

import java.io.Serializable;
import java.util.UUID;

public record BookId(UUID value) implements Serializable {

    public BookId {
        if (value == null) {
            throw new IllegalArgumentException("BookId cannot be null");
        }
    }

    public static BookId fromString(String raw) {
        return new BookId(UUID.fromString(raw));
    }

}
