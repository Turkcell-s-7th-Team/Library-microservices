package com.TurkcellTakim7.reservation_service.domain.valueobjects;

import java.util.UUID;

public record BookId(UUID value) {
    public BookId {
        if (value == null) {
            throw new IllegalArgumentException("BookId cannot be null!");
        }
    }

    public static BookId from(UUID id) {
        return new BookId(id);
    }
}
