package com.TurkcellTakim7.category_service.application.mappers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.category_service.application.dtos.CreatedCategoryResponse;
import com.TurkcellTakim7.category_service.domain.entities.Category;

@Component
public class CreateCategoryMapper {

    public CreatedCategoryResponse toResponse(Category category) {
        if (category == null) {
            return null;
        }
        return new CreatedCategoryResponse(category.getId(), category.getName());
    }
}
