package com.TurkcellTakim7.reservation_service.domain.enums;

public enum ReservationStatus {
    PENDING, // sirada, beklemede
    WAITING_FOR_PICKUP, // almaya hazir bekliyor
    FULFILLED, // alindi
    CANCELLED, // iptal edildi
    EXPIRED; // zaman asimina ugradi rezervasyon

    public boolean isTerminal() {
        return this == FULFILLED || this == CANCELLED || this == EXPIRED;
    }

    public boolean isActive() {
        return this == PENDING || this == WAITING_FOR_PICKUP;
    }
}
