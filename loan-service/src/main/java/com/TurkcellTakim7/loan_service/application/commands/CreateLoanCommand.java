package com.TurkcellTakim7.loan_service.application.commands;

import java.time.LocalDate;
import java.util.UUID;

import com.TurkcellTakim7.loan_service.application.core.command.Command;
import com.TurkcellTakim7.loan_service.application.dtos.CreatedLoanResponse;

public record CreateLoanCommand(
                UUID memberId,
                UUID bookId,
                UUID staffId,
                LocalDate loanDate,
                LocalDate dueDate) implements Command<CreatedLoanResponse> {

}
