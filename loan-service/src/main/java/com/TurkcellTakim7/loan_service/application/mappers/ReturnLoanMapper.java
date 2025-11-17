package com.TurkcellTakim7.loan_service.application.mappers;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.loan_service.application.commands.ReturnLoanCommand;
import com.TurkcellTakim7.loan_service.domain.valueobjects.LoanId;

@Component
public class ReturnLoanMapper {

    public LoanId toLoanId(ReturnLoanCommand command) {
        return new LoanId(UUID.fromString(command.loanId()));
    }

    public LocalDate toReturnDate(ReturnLoanCommand command) {
        return command.returnDate() != null
                ? command.returnDate()
                : LocalDate.now();
    }
}
