package com.TurkcellTakim7.reservation_service.domain.valueobjects;

import java.util.UUID;

public record ReservationId(UUID value) {
    public ReservationId {
        if (value == null) {
            throw new IllegalArgumentException("ReservationId cannot be null!");
        }
    }

    public static ReservationId generate() {
        return new ReservationId(UUID.randomUUID());
    }
}
