package com.TurkcellTakim7.loan_service.application.commandHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.loan_service.application.commands.ReturnLoanCommand;
import com.TurkcellTakim7.loan_service.application.core.command.CommandHandler;
import com.TurkcellTakim7.loan_service.application.mappers.ReturnLoanMapper;
import com.TurkcellTakim7.loan_service.domain.services.LoanDomainService;

@Service
public class ReturnLoanCommandHandler implements CommandHandler<ReturnLoanCommand, Void> {

    private final LoanDomainService loanDomainService;
    private final ReturnLoanMapper returnLoanMapper;

    public ReturnLoanCommandHandler(LoanDomainService loanDomainService,
            ReturnLoanMapper returnLoanMapper) {
        this.loanDomainService = loanDomainService;
        this.returnLoanMapper = returnLoanMapper;
    }

    @Override
    public Void handle(ReturnLoanCommand command) {

        // tarih varsa onu kullan, yoksa mapper içinde now() geliyor
        var returnDate = returnLoanMapper.toReturnDate(command);

        loanDomainService.returnLoan(command.loanId(), returnDate);

        return null;
    }
}
