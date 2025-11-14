package com.TurkcellTakim7.reservation_service.domain.exceptions;

/**
 * Reservation ile ilgili genel doğrulama (validation) hataları için.
 *
 * Örn:
 * - queuePosition < 1
 * - geçersiz parametre kombinasyonları
 */
public class ReservationValidationException extends RuntimeException {

    public ReservationValidationException(String message) {
        super(message);
    }
}
