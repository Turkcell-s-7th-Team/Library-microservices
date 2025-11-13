package com.TurkcellTakim7.loan_service.application.commands;

import java.time.LocalDate;

import com.TurkcellTakim7.loan_service.application.core.command.Command;
import com.TurkcellTakim7.loan_service.application.dtos.CreatedLoanResponse;

public record CreateLoanCommand(String memberId,
        String bookId,
        String staffId,
        LocalDate loanDate,
        LocalDate dueDate) implements Command<CreatedLoanResponse> {

}
