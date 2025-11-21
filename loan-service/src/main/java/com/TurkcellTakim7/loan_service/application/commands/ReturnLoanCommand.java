package com.TurkcellTakim7.loan_service.application.commands;

import java.time.LocalDate;
import java.util.UUID;

import com.TurkcellTakim7.loan_service.application.core.command.Command;

public record ReturnLoanCommand(
        UUID loanId,
        LocalDate returnDate) implements Command<Void> {
}
