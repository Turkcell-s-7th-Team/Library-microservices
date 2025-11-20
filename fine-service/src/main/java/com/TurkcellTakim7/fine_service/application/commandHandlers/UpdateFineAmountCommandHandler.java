package com.TurkcellTakim7.fine_service.application.commandHandlers;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.TurkcellTakim7.fine_service.application.commands.UpdateFineAmountCommand;
import com.TurkcellTakim7.fine_service.application.core.CommandHandler;
import com.TurkcellTakim7.fine_service.application.dto.UpdatedFineResponse;
import com.TurkcellTakim7.fine_service.application.mappers.UpdateFineMapper;
import com.TurkcellTakim7.fine_service.domain.entities.Fine;
import com.TurkcellTakim7.fine_service.domain.repositories.FineRepository;
import com.TurkcellTakim7.fine_service.domain.services.FineDomainService;
import com.TurkcellTakim7.fine_service.domain.valueobjects.Amount;
import com.TurkcellTakim7.fine_service.domain.valueobjects.FineId;


@Component
@Transactional
public class UpdateFineAmountCommandHandler
        implements CommandHandler<UpdateFineAmountCommand, UpdatedFineResponse> {

    private final FineDomainService fineDomainService;
    private final UpdateFineMapper updateFineMapper;
    private final FineRepository fineRepository;

    public UpdateFineAmountCommandHandler(FineDomainService fineDomainService,
                                          UpdateFineMapper updateFineMapper,
                                          FineRepository fineRepository) {
        this.fineDomainService = fineDomainService;
        this.updateFineMapper = updateFineMapper;
        this.fineRepository = fineRepository;
    }

    @Override
    public UpdatedFineResponse handle(UpdateFineAmountCommand command) {
        FineId fineId = new FineId(command.fineId());

        Fine updatedFine = fineDomainService.updateFineAmount(
                fineId,
                new Amount(command.amount())
        );

        updatedFine = fineRepository.save(updatedFine);

        return updateFineMapper.toResponse(updatedFine);
    }
}
