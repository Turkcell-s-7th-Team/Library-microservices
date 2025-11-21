package com.TurkcellTakim7.loan_service.application.events;

import java.time.LocalDate;
import java.util.UUID;

public record LoanReturnedEvent(
        UUID loanId,
        UUID bookId,
        LocalDate returnedAt) {
}
