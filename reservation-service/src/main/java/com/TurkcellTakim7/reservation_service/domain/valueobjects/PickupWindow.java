package com.TurkcellTakim7.reservation_service.domain.valueobjects;

import java.time.Instant;
import java.util.Objects;

/**
 * Kitabın kullanıcı için ayrıldığı zaman penceresi.
 */
public record PickupWindow(Instant startAt, Instant expiresAt) {

    public PickupWindow {
        Objects.requireNonNull(startAt, "PickupWindow.startAt cannot be null");
        Objects.requireNonNull(expiresAt, "PickupWindow.expiresAt cannot be null");

        if (expiresAt.isBefore(startAt)) {
            throw new IllegalArgumentException("expiresAt must be after startAt");
        }
    }

    public boolean isActiveAt(Instant now) {
        return !now.isBefore(startAt) && now.isBefore(expiresAt);
    }

    public boolean isExpiredAt(Instant now) {
        return now.isAfter(expiresAt);
    }
}
