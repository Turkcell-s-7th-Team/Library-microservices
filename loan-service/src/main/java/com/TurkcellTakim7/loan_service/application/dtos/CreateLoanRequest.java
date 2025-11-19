package com.TurkcellTakim7.loan_service.application.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record CreateLoanRequest(
                UUID memberId,
                UUID bookId,
                UUID staffId,
                LocalDate loanDate,
                LocalDate dueDate) {
}
