package com.TurkcellTakim7.reservation_service.domain.exceptions;

import java.util.UUID;

import com.TurkcellTakim7.reservation_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.MemberId;

/**
 * Aynı member + book için aktif rezervasyon varken
 * yeniden rezervasyon oluşturmaya çalışıldığında fırlatılır.
 */
public class ActiveReservationExistsException extends RuntimeException {

    public ActiveReservationExistsException(UUID memberId, UUID bookId) {
        super("Member already has an active reservation for this book. memberId=%s, bookId=%s"
                .formatted(memberId, bookId));
    }

    public ActiveReservationExistsException(MemberId memberId, BookId bookId) {
        super("Member already has an active reservation for this book. memberId=%s, bookId=%s"
                .formatted(memberId.value(), bookId.value()));
    }
}
