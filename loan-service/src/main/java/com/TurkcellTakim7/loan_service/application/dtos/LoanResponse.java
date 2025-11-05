package com.TurkcellTakim7.loan_service.application.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record LoanResponse(UUID id,
        String memberId,
        String bookId,
        String staffId,
        LocalDate loanDate,
        LocalDate dueDate,
        LocalDate returnDate,
        String status) {

}
