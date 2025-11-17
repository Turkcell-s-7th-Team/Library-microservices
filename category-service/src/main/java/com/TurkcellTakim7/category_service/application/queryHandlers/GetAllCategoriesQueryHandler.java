package com.TurkcellTakim7.category_service.application.queryHandlers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.category_service.application.core.QueryHandler;
import com.TurkcellTakim7.category_service.application.dtos.CategoryResponse;
import com.TurkcellTakim7.category_service.application.mappers.CategoryResponseMapper;
import com.TurkcellTakim7.category_service.application.queries.GetAllCategoriesQuery;
import com.TurkcellTakim7.category_service.domain.services.CategoryDomainService;

@Service
public class GetAllCategoriesQueryHandler implements QueryHandler<GetAllCategoriesQuery, List<CategoryResponse>> {

    private final CategoryDomainService service;
    private final CategoryResponseMapper mapper;

    public GetAllCategoriesQueryHandler(CategoryDomainService service, CategoryResponseMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<CategoryResponse> handle(GetAllCategoriesQuery query) {
        var categories = service.getAll();
        return mapper.toResponseList(categories);
    }
}
