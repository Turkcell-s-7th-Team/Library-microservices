package com.TurkcellTakim7.category_service.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.category_service.domain.entities.Category;
import com.TurkcellTakim7.category_service.domain.exceptions.CategoryAlreadyExistsException;
import com.TurkcellTakim7.category_service.domain.exceptions.CategoryNotFoundException;
import com.TurkcellTakim7.category_service.domain.exceptions.CategoryValidationException;
import com.TurkcellTakim7.category_service.domain.repositories.CategoryRepository;

@Service
public class CategoryDomainService {

    private final CategoryRepository repository;

    public CategoryDomainService(CategoryRepository repository) {
        this.repository = repository;
    }

    /** CREATE */
    public Category create(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new CategoryValidationException("Category name cannot be empty");
        }
        if (repository.existsByName(name)) {
            throw new CategoryAlreadyExistsException(name);
        }
        Category category = new Category(name);
        return repository.save(category);
    }

    /** UPDATE */
    public Category update(String id, String name, boolean active) {
        Optional<Category> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new CategoryNotFoundException(id);
        }
        if (name == null || name.trim().isEmpty()) {
            throw new CategoryValidationException("Category name cannot be empty");
        }

        Category category = optional.get();
        category.setName(name);
        category.setActive(active);

        return repository.save(category);
    }

    /** DELETE → Category döndürmeli (Handler işleyecek) */
    public Category delete(String id) {
        Optional<Category> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new CategoryNotFoundException(id);
        }

        Category category = optional.get();
        repository.delete(category);

        return category;
    }

    /** GET BY ID */
    public Category getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    /** GET ALL */
    public List<Category> getAll() {
        return repository.findAll();
    }
}
