package com.TurkcellTakim7.reservation_service.domain.exceptions;

/**
 * Aynı member + book için aktif rezervasyon varken
 * yeniden rezervasyon oluşturmaya çalışıldığında fırlatılır.
 */
public class ActiveReservationExistsException extends RuntimeException {

    public ActiveReservationExistsException(String memberId, String bookId) {
        super("Member already has an active reservation for this book. memberId=%s, bookId=%s"
                .formatted(memberId, bookId));
    }
}
