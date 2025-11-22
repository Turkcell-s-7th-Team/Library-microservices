package com.TurkcellTakim7.book_service.infrastructure.messaging.event;

import java.time.LocalDate;
import java.util.UUID;

public record LoanReturnedEvent(
    UUID loanId,
    UUID bookId,
    LocalDate returnedAt) {
}
