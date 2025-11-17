package com.TurkcellTakim7.loan_service.application.commands;

import com.TurkcellTakim7.loan_service.application.core.command.Command;

public record ExtendLoanCommand(
        String loanId,
        int days) implements Command<Void> {
}
