package com.TurkcellTakim7.category_service.application.commandHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.category_service.application.commands.DeleteCategoryCommand;
import com.TurkcellTakim7.category_service.domain.services.CategoryDomainService;

@Service
public class DeleteCategoryCommandHandler {

    private final CategoryDomainService service;

    public DeleteCategoryCommandHandler(CategoryDomainService service) {
        this.service = service;
    }

    public void handle(DeleteCategoryCommand command) {
        service.delete(command.getId());
    }
}
