package com.TurkcellTakim7.category_service.application.commandHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.category_service.application.commands.DeleteCategoryCommand;
import com.TurkcellTakim7.category_service.application.core.CommandHandler;
import com.TurkcellTakim7.category_service.application.dtos.CategoryResponse;
import com.TurkcellTakim7.category_service.application.mappers.CategoryResponseMapper;
import com.TurkcellTakim7.category_service.domain.services.CategoryDomainService;

@Service
public class DeleteCategoryCommandHandler implements CommandHandler<DeleteCategoryCommand, CategoryResponse> {

    private final CategoryDomainService service;
    private final CategoryResponseMapper mapper;

    public DeleteCategoryCommandHandler(CategoryDomainService service, CategoryResponseMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public CategoryResponse handle(DeleteCategoryCommand command) {
        var category = service.delete(command.id());
        return mapper.toResponse(category);
    }
}
