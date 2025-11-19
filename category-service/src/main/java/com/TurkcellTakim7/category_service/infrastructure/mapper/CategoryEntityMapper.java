package com.TurkcellTakim7.category_service.infrastructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.category_service.domain.entities.Category;
import com.TurkcellTakim7.category_service.infrastructure.entities.CategoryEntity;

@Component
public class CategoryEntityMapper {

    public Category toDomain(CategoryEntity entity) {
        if (entity == null)
            return null;
        Category category = new Category();
        category.setName(entity.getName());
        category.setActive(entity.isActive());
        category.setUpdatedDate(entity.getUpdatedDate());
        category.setCreatedDate(entity.getCreatedDate());
        try {
            var idField = Category.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(category, entity.getId());
        } catch (Exception ignored) {
        }
        return category;
    }

    public CategoryEntity toEntity(Category category) {
        if (category == null)
            return null;
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId());
        entity.setName(category.getName());
        entity.setActive(category.isActive());
        entity.setCreatedDate(category.getCreatedDate());
        entity.setUpdatedDate(category.getUpdatedDate());
        return entity;
    }

    public List<Category> toDomainList(List<CategoryEntity> entities) {
        return entities.stream().map(this::toDomain).collect(Collectors.toList());
    }
}
