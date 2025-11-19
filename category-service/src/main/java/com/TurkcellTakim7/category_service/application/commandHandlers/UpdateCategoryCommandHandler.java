package com.TurkcellTakim7.category_service.application.commandHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.category_service.application.commands.UpdateCategoryCommand;
import com.TurkcellTakim7.category_service.application.core.CommandHandler;
import com.TurkcellTakim7.category_service.application.dtos.CategoryResponse;
import com.TurkcellTakim7.category_service.application.mappers.CategoryResponseMapper;
import com.TurkcellTakim7.category_service.domain.services.CategoryDomainService;

@Service
public class UpdateCategoryCommandHandler implements CommandHandler<UpdateCategoryCommand, CategoryResponse> {

    private final CategoryDomainService service;
    private final CategoryResponseMapper mapper;

    public UpdateCategoryCommandHandler(CategoryDomainService service, CategoryResponseMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public CategoryResponse handle(UpdateCategoryCommand command) {
        var category = service.update(command.id(), command.name(), command.active());
        return mapper.toResponse(category);
    }
}
