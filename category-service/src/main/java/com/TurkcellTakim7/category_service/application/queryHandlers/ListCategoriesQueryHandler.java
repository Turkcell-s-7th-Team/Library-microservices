package com.TurkcellTakim7.category_service.application.queryHandlers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.category_service.application.core.QueryHandler;
import com.TurkcellTakim7.category_service.application.queries.ListCategoriesQuery;
import com.TurkcellTakim7.category_service.domain.entities.Category;
import com.TurkcellTakim7.category_service.domain.services.CategoryDomainService;

@Service
public class ListCategoriesQueryHandler implements QueryHandler<ListCategoriesQuery, List<Category>> {

    private final CategoryDomainService service;

    public ListCategoriesQueryHandler(CategoryDomainService service) {
        this.service = service;
    }

    @Override
    public List<Category> handle(ListCategoriesQuery query) {
        return service.getAll();
    }
}
