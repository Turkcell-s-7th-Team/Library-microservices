package com.TurkcellTakim7.loan_service.application.commandHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.loan_service.application.commands.ExtendLoanCommand;
import com.TurkcellTakim7.loan_service.application.core.command.CommandHandler;
import com.TurkcellTakim7.loan_service.application.mappers.ExtendLoanMapper;
import com.TurkcellTakim7.loan_service.domain.services.LoanDomainService;

@Service
public class ExtendLoanCommandHandler implements CommandHandler<ExtendLoanCommand, Void> {

    private final LoanDomainService loanDomainService;
    private final ExtendLoanMapper extendLoanMapper;

    public ExtendLoanCommandHandler(LoanDomainService loanDomainService,
                                    ExtendLoanMapper extendLoanMapper) {
        this.loanDomainService = loanDomainService;
        this.extendLoanMapper = extendLoanMapper;
    }

    @Override
    public Void handle(ExtendLoanCommand command) {

        int days = extendLoanMapper.toDays(command);

        loanDomainService.extendLoan(command.loanId(), days);

        return null;
    }
}
