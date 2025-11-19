package com.TurkcellTakim7.category_service.domain.exceptions;

public class CategoryNotFoundException extends DomainException {
    public CategoryNotFoundException(String id) {
        super("Category not found with id: " + id);
    }
}
