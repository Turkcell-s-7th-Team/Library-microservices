package com.TurkcellTakim7.reservation_service.domain.valueobjects;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Reservation aggregate için kimlik VO'su.
 * İçeride sadece String tutar, generate() UUID üretip String'e çevirir.
 */
public record ReservationId(String value) implements Serializable {

    public ReservationId {
        Objects.requireNonNull(value, "ReservationId value cannot be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException("ReservationId value cannot be blank");
        }
    }

    public static ReservationId generate() {
        return new ReservationId(UUID.randomUUID().toString());
    }

    public static ReservationId of(String raw) {
        // İstersen burada UUID format validation da yapabilirsin:
        // UUID.fromString(raw);
        return new ReservationId(raw);
    }

    @Override
    public String toString() {
        return value;
    }
}
