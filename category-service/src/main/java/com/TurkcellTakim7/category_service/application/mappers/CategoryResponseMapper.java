package com.TurkcellTakim7.category_service.application.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.category_service.application.dtos.CategoryResponse;
import com.TurkcellTakim7.category_service.domain.entities.Category;

@Component
public class CategoryResponseMapper {

    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.isActive(),
                category.getCreatedDate(),
                category.getUpdatedDate());
    }

    public List<CategoryResponse> toResponseList(List<Category> categories) {
        return categories.stream()
                .map(this::toResponse)
                .toList();
    }
}
