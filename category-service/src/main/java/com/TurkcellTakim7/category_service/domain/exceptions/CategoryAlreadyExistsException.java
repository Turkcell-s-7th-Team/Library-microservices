package com.TurkcellTakim7.category_service.domain.exceptions;

public class CategoryAlreadyExistsException extends DomainException {
    public CategoryAlreadyExistsException(String name) {
        super("Category already exists with name: " + name);
    }
}
