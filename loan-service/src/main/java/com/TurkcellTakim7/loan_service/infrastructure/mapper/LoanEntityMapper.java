package com.TurkcellTakim7.loan_service.infrastructure.mapper;

import com.TurkcellTakim7.loan_service.domain.entities.Loan;
import com.TurkcellTakim7.loan_service.domain.enums.LoanStatus;
import com.TurkcellTakim7.loan_service.domain.valueobjects.*;
import com.TurkcellTakim7.loan_service.infrastructure.entities.LoanEntity;
import org.springframework.stereotype.Component;

@Component
public class LoanEntityMapper {

    public LoanEntity toEntity(Loan loan) {
        LoanEntity e = new LoanEntity();
        e.setId(loan.id().value());
        e.setMemberId(loan.memberId().value());
        e.setBookId(loan.bookId().value());
        e.setStaffId(loan.staffId().value());
        e.setLoanDate(loan.loanDate());
        e.setDueDate(loan.dueDate());
        e.setReturnDate(loan.returnDate());
        e.setStatus(loan.status().name());
        return e;
    }

    public Loan toDomain(LoanEntity e) {
        return Loan.rehydrate(
                new LoanId(e.getId()),
                new MemberId(e.getMemberId()),
                new BookId(e.getBookId()),
                new StaffId(e.getStaffId()),
                e.getLoanDate(),
                e.getDueDate(),
                e.getReturnDate(),
                LoanStatus.valueOf(e.getStatus())
        );
    }
}
