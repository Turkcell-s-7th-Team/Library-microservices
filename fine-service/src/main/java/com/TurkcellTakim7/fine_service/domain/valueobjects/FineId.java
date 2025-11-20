package com.TurkcellTakim7.fine_service.domain.valueobjects;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record FineId(UUID value) implements Serializable {

    public FineId {
        Objects.requireNonNull(value, "Value for FineId cannot be null");
    }

    public static FineId generate() {
        return new FineId(UUID.randomUUID());
    }
}
