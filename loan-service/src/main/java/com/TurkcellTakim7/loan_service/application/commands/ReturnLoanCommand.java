package com.TurkcellTakim7.loan_service.application.commands;

import java.time.LocalDate;

import com.TurkcellTakim7.loan_service.application.core.command.Command;

public record ReturnLoanCommand(
        String loanId,
        LocalDate returnDate) implements Command<Void> {
}
