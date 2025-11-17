package com.TurkcellTakim7.loan_service.application.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record CreatedLoanResponse(
        UUID id,
        LocalDate loanDate,
        LocalDate dueDate) {
}