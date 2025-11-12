package com.TurkcellTakim7.category_service.application.commandHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.category_service.application.commands.CreateCategoryCommand;
import com.TurkcellTakim7.category_service.domain.entities.Category;
import com.TurkcellTakim7.category_service.domain.services.CategoryDomainService;

@Service
public class CreateCategoryCommandHandler {

    private final CategoryDomainService service;

    public CreateCategoryCommandHandler(CategoryDomainService service) {
        this.service = service;
    }

    public Category handle(CreateCategoryCommand command) {
        return service.create(command.getName());
    }
}
