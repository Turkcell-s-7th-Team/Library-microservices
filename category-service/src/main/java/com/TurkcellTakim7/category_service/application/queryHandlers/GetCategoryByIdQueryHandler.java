package com.TurkcellTakim7.category_service.application.queryHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.category_service.application.core.QueryHandler;
import com.TurkcellTakim7.category_service.application.dtos.CategoryResponse;
import com.TurkcellTakim7.category_service.application.mappers.CategoryResponseMapper;
import com.TurkcellTakim7.category_service.application.queries.GetCategoryByIdQuery;
import com.TurkcellTakim7.category_service.domain.services.CategoryDomainService;

@Service
public class GetCategoryByIdQueryHandler implements QueryHandler<GetCategoryByIdQuery, CategoryResponse> {

    private final CategoryDomainService service;
    private final CategoryResponseMapper mapper;

    public GetCategoryByIdQueryHandler(CategoryDomainService service, CategoryResponseMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public CategoryResponse handle(GetCategoryByIdQuery query) {
        var category = service.getById(query.id());
        return mapper.toResponse(category);
    }
}
