package com.TurkcellTakim7.loan_service.application.mappers;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.loan_service.application.commands.ReturnLoanCommand;

@Component
public class ReturnLoanMapper {

    public LocalDate toReturnDate(ReturnLoanCommand command) {
        return command.returnDate() != null
                ? command.returnDate()
                : LocalDate.now();
    }
}
