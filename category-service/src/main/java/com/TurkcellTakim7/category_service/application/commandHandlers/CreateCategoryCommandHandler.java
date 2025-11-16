package com.TurkcellTakim7.category_service.application.commandHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.category_service.application.commands.CreateCategoryCommand;
import com.TurkcellTakim7.category_service.application.core.CommandHandler;
import com.TurkcellTakim7.category_service.application.dtos.CategoryResponse;
import com.TurkcellTakim7.category_service.application.mappers.CategoryResponseMapper;
import com.TurkcellTakim7.category_service.domain.services.CategoryDomainService;

@Service
public class CreateCategoryCommandHandler implements CommandHandler<CreateCategoryCommand, CategoryResponse> {

    private final CategoryDomainService service;
    private final CategoryResponseMapper mapper;

    public CreateCategoryCommandHandler(CategoryDomainService service, CategoryResponseMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public CategoryResponse handle(CreateCategoryCommand command) {
        var category = service.create(command.name());
        return mapper.toResponse(category);
    }
}
