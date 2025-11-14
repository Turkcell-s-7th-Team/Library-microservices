package com.TurkcellTakim7.loan_service.application.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.loan_service.application.dtos.LoanResponse;
import com.TurkcellTakim7.loan_service.domain.entities.Loan;

@Component
public class LoanResponseMapper {

    public LoanResponse toResponse(Loan loan) {
        return new LoanResponse(
                loan.id().value(),
                loan.memberId().value().toString(),
                loan.bookId().value().toString(),
                loan.staffId().value().toString(),
                loan.loanDate(),
                loan.dueDate(),
                loan.returnDate(),
                loan.status().name());
    }

    public List<LoanResponse> toResponseList(List<Loan> loans) {
        return loans.stream()
                .map(this::toResponse)
                .toList();
    }
}
