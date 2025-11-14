package com.TurkcellTakim7.loan_service.application.mappers;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.loan_service.application.commands.CreateLoanCommand;
import com.TurkcellTakim7.loan_service.application.dtos.CreatedLoanResponse;
import com.TurkcellTakim7.loan_service.domain.entities.Loan;
import com.TurkcellTakim7.loan_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.loan_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.loan_service.domain.valueobjects.StaffId;

@Component
public class CreateLoanMapper {

    public Loan toDomain(CreateLoanCommand command) {
        return Loan.create(
                new MemberId(UUID.fromString(command.memberId())),
                new BookId(UUID.fromString(command.bookId())),
                new StaffId(UUID.fromString(command.staffId())),
                command.loanDate(),
                command.dueDate());
    }

    public CreatedLoanResponse toResponse(Loan loan) {
        return new CreatedLoanResponse(
                loan.id().value(),
                loan.loanDate(),
                loan.dueDate());
    }
}