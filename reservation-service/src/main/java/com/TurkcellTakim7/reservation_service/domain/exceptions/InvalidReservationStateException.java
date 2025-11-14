package com.TurkcellTakim7.reservation_service.domain.exceptions;

/**
 * Reservation üzerinde geçersiz bir durum geçişi (state transition)
 * yapılmaya çalışıldığında kullanılır.
 *
 * Örn:
 * - PENDING olmayan bir rezervasyonu WAITING_FOR_PICKUP yapmak
 * - WAITING_FOR_PICKUP olmayan bir rezervasyonu FULFILLED yapmak
 */
public class InvalidReservationStateException extends RuntimeException {

    public InvalidReservationStateException(String message) {
        super(message);
    }
}
