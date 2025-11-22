package com.TurkcellTakim7.loan_service.application.commandHandlers;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.TurkcellTakim7.loan_service.application.commands.CreateLoanCommand;
import com.TurkcellTakim7.loan_service.application.core.command.CommandHandler;
import com.TurkcellTakim7.loan_service.application.dtos.CreatedLoanResponse;
import com.TurkcellTakim7.loan_service.application.events.LoanCreatedEvent;
import com.TurkcellTakim7.loan_service.application.exception.MemberIsBannedException;
import com.TurkcellTakim7.loan_service.application.exception.NoAvailableCopyException;
import com.TurkcellTakim7.loan_service.application.mappers.CreateLoanMapper;
import com.TurkcellTakim7.loan_service.domain.entities.Loan;
import com.TurkcellTakim7.loan_service.domain.services.LoanDomainService;
import com.TurkcellTakim7.loan_service.infrastructure.adapter.feignClients.bookClient.BookClient;
import com.TurkcellTakim7.loan_service.infrastructure.adapter.feignClients.memberClient.MemberClient;
import com.TurkcellTakim7.loan_service.infrastructure.adapter.feignClients.memberClient.dto.MemberValidationDTO;
import com.TurkcellTakim7.loan_service.infrastructure.messaging.LoanEventPublisher;

@Service
public class CreateLoanCommandHandler implements CommandHandler<CreateLoanCommand, CreatedLoanResponse> {

    private final LoanDomainService loanDomainService;
    private final CreateLoanMapper createLoanMapper;
    private final MemberClient memberClient;
    private final BookClient bookClient;
    private final LoanEventPublisher loanEventPublisher;
    private final StreamBridge streamBridge;

    public CreateLoanCommandHandler(LoanDomainService loanDomainService, CreateLoanMapper createLoanMapper,
            MemberClient memberClient, BookClient bookClient, LoanEventPublisher loanEventPublisher,
            StreamBridge streamBridge) {
        this.loanDomainService = loanDomainService;
        this.createLoanMapper = createLoanMapper;
        this.memberClient = memberClient;
        this.bookClient = bookClient;
        this.loanEventPublisher = loanEventPublisher;
        this.streamBridge = streamBridge;
    }

    public CreatedLoanResponse handle(CreateLoanCommand command) {

        MemberValidationDTO memberValidationDTO = memberClient.getMemberValidationInfo(command.memberId());

        // BANNED kontrolü
        if (memberValidationDTO.membershipLevel().value().equals("BANNED")) {
            throw new MemberIsBannedException();
        }
        // Available copy kontrolü
        Boolean isAvailable = bookClient.getBookValidationInfo(command.bookId());
        if (!isAvailable) {
            throw new NoAvailableCopyException();
        }

        Loan loan = loanDomainService.createLoan(
                command.memberId().toString(),
                command.bookId().toString(),
                command.staffId().toString(),
                command.loanDate(),
                command.dueDate());

        LoanCreatedEvent event = new LoanCreatedEvent(loan.id().value(), loan.bookId().value(), loan.memberId().value(),
                loan.loanDate(), loan.dueDate());
        streamBridge.send("loanCreated-out-0", event);
        return createLoanMapper.toResponse(loan);
    }

}
