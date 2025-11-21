package com.TurkcellTakim7.reservation_service.infrastructure.messaging.events;

import java.time.LocalDate;
import java.util.UUID;

public record LoanCreatedEvent(
        UUID loanId,
        UUID bookId,
        UUID memberId,
        LocalDate loanDate,
        LocalDate dueDate) {
}
