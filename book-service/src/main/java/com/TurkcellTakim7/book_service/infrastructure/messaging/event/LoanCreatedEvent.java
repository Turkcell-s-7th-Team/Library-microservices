package com.TurkcellTakim7.book_service.infrastructure.messaging.event;

import java.time.LocalDate;
import java.util.UUID;

public record LoanCreatedEvent(
    UUID loanId,
    UUID bookId,
    UUID memberId,
    LocalDate loanDate,
    LocalDate dueDate) {
}