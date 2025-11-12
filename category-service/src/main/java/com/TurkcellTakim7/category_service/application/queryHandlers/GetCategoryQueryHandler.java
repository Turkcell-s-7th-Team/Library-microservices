package com.TurkcellTakim7.category_service.application.queryHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.category_service.application.core.QueryHandler;
import com.TurkcellTakim7.category_service.application.queries.GetCategoryQuery;
import com.TurkcellTakim7.category_service.domain.entities.Category;
import com.TurkcellTakim7.category_service.domain.services.CategoryDomainService;

@Service
public class GetCategoryQueryHandler implements QueryHandler<GetCategoryQuery, Category> {

    private final CategoryDomainService service;

    public GetCategoryQueryHandler(CategoryDomainService service) {
        this.service = service;
    }

    @Override
    public Category handle(GetCategoryQuery query) {
        return service.getById(query.getId());
    }
}
