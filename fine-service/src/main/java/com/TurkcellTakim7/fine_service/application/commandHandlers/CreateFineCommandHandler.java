package com.TurkcellTakim7.fine_service.application.commandHandlers;

import org.springframework.cloud.stream.function.StreamBridge;
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
import com.TurkcellTakim7.fine_service.infrastructure.messaging.events.FineCreatedEvent;

@Component
public class CreateFineCommandHandler implements CommandHandler<CreateFineCommand, CreatedFineResponse> {

    private final CreateFineMapper createFineMapper;
    private final FineDomainService fineDomainService;
    private final FineRepository fineRepository;
    private final StreamBridge streamBridge;

    public CreateFineCommandHandler(CreateFineMapper createFineMapper, FineDomainService fineDomainService,
            FineRepository fineRepository, StreamBridge streamBridge) {
        this.createFineMapper = createFineMapper;
        this.fineDomainService = fineDomainService;
        this.fineRepository = fineRepository;
        this.streamBridge = streamBridge;
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
                amount);

        fine = fineRepository.save(fine);

        FineCreatedEvent event = new FineCreatedEvent(
                fine.getMemberId().value());
        streamBridge.send("fineCreated-out-0", event);
        System.out.println("event gönderildi");
        return createFineMapper.toResponse(fine);
    }
}
