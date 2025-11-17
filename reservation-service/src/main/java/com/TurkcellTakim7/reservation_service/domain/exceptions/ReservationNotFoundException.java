package com.TurkcellTakim7.reservation_service.domain.exceptions;

/**
 * Verilen id ile herhangi bir Reservation bulunamadığında fırlatılır.
 */
public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(String id) {
        super("Reservation not found with id: " + id);
    }
}
