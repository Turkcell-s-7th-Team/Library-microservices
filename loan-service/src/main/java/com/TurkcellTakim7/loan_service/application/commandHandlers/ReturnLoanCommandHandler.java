package com.TurkcellTakim7.loan_service.application.commandHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.loan_service.application.commands.ReturnLoanCommand;
import com.TurkcellTakim7.loan_service.application.core.command.CommandHandler;
import com.TurkcellTakim7.loan_service.application.events.LoanReturnedEvent;
import com.TurkcellTakim7.loan_service.application.mappers.ReturnLoanMapper;
import com.TurkcellTakim7.loan_service.domain.entities.Loan;
import com.TurkcellTakim7.loan_service.domain.services.LoanDomainService;
import com.TurkcellTakim7.loan_service.infrastructure.messaging.LoanEventPublisher;

@Service
public class ReturnLoanCommandHandler implements CommandHandler<ReturnLoanCommand, Void> {

    private final LoanDomainService loanDomainService;
    private final ReturnLoanMapper returnLoanMapper;
    private final LoanEventPublisher loanEventPublisher;

    public ReturnLoanCommandHandler(LoanDomainService loanDomainService,
                                    ReturnLoanMapper returnLoanMapper,
                                    LoanEventPublisher loanEventPublisher) {
        this.loanDomainService = loanDomainService;
        this.returnLoanMapper = returnLoanMapper;
        this.loanEventPublisher = loanEventPublisher;
    }

    @Override
    public Void handle(ReturnLoanCommand command) {

        // 1) Tarihi hesapla (varsa command'den, yoksa now)
        var returnDate = returnLoanMapper.toReturnDate(command);

        // 2) Domain iş kuralını çalıştır → Loan döner
        Loan loan = loanDomainService.returnLoan(command.loanId(), returnDate);

        // 3) Event üret ve publish et
        LoanReturnedEvent event = new LoanReturnedEvent(
                loan.id().value(),      // LoanId VO -> UUID
                loan.bookId().value(),  // BookId VO -> UUID
                loan.returnDate()       // LocalDate
        );
        loanEventPublisher.publishLoanReturned(event);

        return null;
    }
}
