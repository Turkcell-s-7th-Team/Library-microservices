package com.TurkcellTakim7.loan_service.application.dtos;

import java.time.LocalDate;

public record ReturnLoanRequest(
        LocalDate returnDate) {
}
