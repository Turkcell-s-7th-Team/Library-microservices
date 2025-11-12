package com.TurkcellTakim7.category_service.application.commandHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.category_service.application.commands.UpdateCategoryCommand;
import com.TurkcellTakim7.category_service.domain.entities.Category;
import com.TurkcellTakim7.category_service.domain.services.CategoryDomainService;

@Service
public class UpdateCategoryCommandHandler {

    private final CategoryDomainService service;

    public UpdateCategoryCommandHandler(CategoryDomainService service) {
        this.service = service;
    }

    public Category handle(UpdateCategoryCommand command) {
        return service.update(command.getId(), command.getName(), command.isActive());
    }
}
