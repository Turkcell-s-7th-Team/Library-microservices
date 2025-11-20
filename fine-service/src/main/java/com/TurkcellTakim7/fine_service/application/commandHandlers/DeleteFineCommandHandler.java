package com.TurkcellTakim7.fine_service.application.commandHandlers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.fine_service.application.commands.DeleteFineCommand;
import com.TurkcellTakim7.fine_service.application.core.CommandHandler;
import com.TurkcellTakim7.fine_service.domain.services.FineDomainService;
import com.TurkcellTakim7.fine_service.domain.valueobjects.FineId;

@Component
public class DeleteFineCommandHandler implements CommandHandler<DeleteFineCommand, Void> {

    private final FineDomainService fineDomainService;

    public DeleteFineCommandHandler(FineDomainService fineDomainService) {
        this.fineDomainService = fineDomainService;
    }

    @Override
    public Void handle(DeleteFineCommand command) {
        FineId fineId = new FineId(command.fineId());
        fineDomainService.deleteById(fineId);
        return null;
    }
}

