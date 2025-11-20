package com.TurkcellTakim7.reservation_service.domain.exceptions;

import java.util.UUID;

import com.TurkcellTakim7.reservation_service.domain.valueobjects.ReservationId;

/**
 * Verilen id ile herhangi bir Reservation bulunamadığında fırlatılır.
 */
public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(UUID id) {
        super("Reservation not found with id: " + id);
    }

    public ReservationNotFoundException(ReservationId id) {
        super("Reservation not found with id: " + id.value());
    }
}
