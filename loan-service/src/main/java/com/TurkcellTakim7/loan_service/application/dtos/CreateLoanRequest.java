package com.TurkcellTakim7.loan_service.application.dtos;

import java.time.LocalDate;

public record CreateLoanRequest(
        String memberId,
        String bookId,
        String staffId,
        LocalDate loanDate,
        LocalDate dueDate) {
}
