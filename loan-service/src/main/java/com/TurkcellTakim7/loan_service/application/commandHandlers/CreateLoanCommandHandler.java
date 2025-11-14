package com.TurkcellTakim7.loan_service.application.commandHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.loan_service.application.commands.CreateLoanCommand;
import com.TurkcellTakim7.loan_service.application.core.command.CommandHandler;
import com.TurkcellTakim7.loan_service.application.dtos.CreatedLoanResponse;
import com.TurkcellTakim7.loan_service.application.mappers.CreateLoanMapper;
import com.TurkcellTakim7.loan_service.domain.entities.Loan;
import com.TurkcellTakim7.loan_service.domain.services.LoanDomainService;

@Service
public class CreateLoanCommandHandler implements CommandHandler<CreateLoanCommand, CreatedLoanResponse> {

    private final LoanDomainService loanDomainService;
    private final CreateLoanMapper createLoanMapper;

    public CreateLoanCommandHandler(LoanDomainService loanDomainService,
                                    CreateLoanMapper createLoanMapper) {
        this.loanDomainService = loanDomainService;
        this.createLoanMapper = createLoanMapper;
    }

    @Override
    public CreatedLoanResponse handle(CreateLoanCommand command) {

        Loan loan = loanDomainService.createLoan(
                command.memberId(),
                command.bookId(),
                command.staffId(),
                command.loanDate(),
                command.dueDate()
        );

        return createLoanMapper.toResponse(loan);
    }
}
