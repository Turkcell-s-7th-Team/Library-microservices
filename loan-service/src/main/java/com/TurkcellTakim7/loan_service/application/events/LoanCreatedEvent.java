package com.TurkcellTakim7.loan_service.application.events;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;

public record LoanCreatedEvent(
    @Nonnull UUID loanId,
    @NotNull UUID bookId,
    @NotNull UUID memberId,
    @NotNull LocalDate loanDate,
    @NotNull LocalDate dueDate) {
}
