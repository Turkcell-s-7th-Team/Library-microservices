package com.TurkcellTakim7.loan_service.application.commandHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.loan_service.application.commands.CreateLoanCommand;
import com.TurkcellTakim7.loan_service.application.core.command.CommandHandler;
import com.TurkcellTakim7.loan_service.application.dtos.CreatedLoanResponse;
import com.TurkcellTakim7.loan_service.application.mappers.CreateLoanMapper;
import com.TurkcellTakim7.loan_service.domain.entities.Loan;
import com.TurkcellTakim7.loan_service.domain.services.LoanDomainService;
import com.TurkcellTakim7.loan_service.infrastructure.adapter.feignClients.bookClient.BookClient;
import com.TurkcellTakim7.loan_service.infrastructure.adapter.feignClients.memberClient.MemberClient;
import com.TurkcellTakim7.loan_service.infrastructure.adapter.feignClients.memberClient.dto.MemberValidationDTO;

@Service
public class CreateLoanCommandHandler implements CommandHandler<CreateLoanCommand, CreatedLoanResponse> {

    private final LoanDomainService loanDomainService;
    private final CreateLoanMapper createLoanMapper;
    private final MemberClient memberClient;
    private final BookClient bookClient;

    public CreateLoanCommandHandler(LoanDomainService loanDomainService, CreateLoanMapper createLoanMapper,
            MemberClient memberClient, BookClient bookClient) {
        this.loanDomainService = loanDomainService;
        this.createLoanMapper = createLoanMapper;
        this.memberClient = memberClient;
        this.bookClient = bookClient;
    }

    public CreatedLoanResponse handle(CreateLoanCommand command) {

        System.out.println("MemberId inside command = " + command.memberId());

        MemberValidationDTO memberValidationDTO = memberClient.getMemberValidationInfo(command.memberId());
        System.out.println("tosit:" + memberValidationDTO);

        // BANNED kontrolü
        if (memberValidationDTO.membershipLevel().value().equals("BANNED")) {
            throw new RuntimeException("Member is banned!");
        }

        Boolean isAvailable = bookClient.getBookValidationInfo(command.bookId());
        if (!isAvailable) {
            throw new RuntimeException("There is no available copy!");
        }

        Loan loan = loanDomainService.createLoan(
                command.memberId().toString(),
                command.bookId().toString(),
                command.staffId().toString(),
                command.loanDate(),
                command.dueDate());

        return createLoanMapper.toResponse(loan);
    }

}
