package com.TurkcellTakim7.book_service.domain.valueobjects;
import java.util.Objects;
import java.util.UUID;

public record PublisherId(UUID value) {
    public PublisherId {
        Objects.requireNonNull(value, "PublisherId cannot be null!");
    }
}