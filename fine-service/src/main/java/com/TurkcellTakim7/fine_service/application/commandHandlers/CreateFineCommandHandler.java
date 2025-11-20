package com.TurkcellTakim7.fine_service.application.commandHandlers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.fine_service.application.commands.CreateFineCommand;
import com.TurkcellTakim7.fine_service.application.core.CommandHandler;
import com.TurkcellTakim7.fine_service.application.dto.CreatedFineResponse;
import com.TurkcellTakim7.fine_service.application.mappers.CreateFineMapper;
import com.TurkcellTakim7.fine_service.domain.entities.Fine;
import com.TurkcellTakim7.fine_service.domain.enums.FineType;
import com.TurkcellTakim7.fine_service.domain.repositories.FineRepository;
import com.TurkcellTakim7.fine_service.domain.services.FineDomainService;
import com.TurkcellTakim7.fine_service.domain.valueobjects.Amount;
import com.TurkcellTakim7.fine_service.domain.valueobjects.LoanId;
import com.TurkcellTakim7.fine_service.domain.valueobjects.MemberId;

@Component
public class CreateFineCommandHandler implements CommandHandler<CreateFineCommand, CreatedFineResponse> {

    private final CreateFineMapper createFineMapper;
    private final FineDomainService fineDomainService;
    private final FineRepository fineRepository;

    public CreateFineCommandHandler(CreateFineMapper createFineMapper,
                                    FineDomainService fineDomainService,
                                    FineRepository fineRepository) {
        this.createFineMapper = createFineMapper;
        this.fineDomainService = fineDomainService;
        this.fineRepository = fineRepository;
    }

    
    public CreatedFineResponse handle(CreateFineCommand command) {

        MemberId memberId = new MemberId(command.memberId());
        LoanId loanId = new LoanId(command.loanId());
        FineType fineType = FineType.valueOf(command.fineType());
        Amount amount = new Amount(command.amount());

        Fine fine = fineDomainService.createFine(
                memberId,
                loanId,
                fineType,
                amount
        );

        fine = fineRepository.save(fine);

        return createFineMapper.toResponse(fine);
    }
}
