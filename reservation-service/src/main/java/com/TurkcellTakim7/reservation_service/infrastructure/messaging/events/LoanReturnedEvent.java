package com.TurkcellTakim7.reservation_service.infrastructure.messaging.events;

import java.time.LocalDate;
import java.util.UUID;

public record LoanReturnedEvent(
                UUID loanId,
                UUID bookId,
                LocalDate returnedAt) {
}
