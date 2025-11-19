package com.TurkcellTakim7.category_service.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import com.TurkcellTakim7.category_service.domain.entities.Category;

public interface CategoryRepository {
    Optional<Category> findById(String id);

    Optional<Category> findByName(String name);

    boolean existsByName(String name);

    boolean existsById(String id);

    Category save(Category category);

    void deleteById(String id);

    List<Category> findAll();
}
