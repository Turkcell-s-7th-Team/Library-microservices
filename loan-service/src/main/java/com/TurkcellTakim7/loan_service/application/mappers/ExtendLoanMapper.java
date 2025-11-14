package com.TurkcellTakim7.loan_service.application.mappers;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.loan_service.application.commands.ExtendLoanCommand;
import com.TurkcellTakim7.loan_service.domain.valueobjects.LoanId;

@Component
public class ExtendLoanMapper {

    public LoanId toLoanId(ExtendLoanCommand command) {
        return new LoanId(UUID.fromString(command.loanId()));
    }

    public int toDays(ExtendLoanCommand command) {
        return command.days();
    }
}
