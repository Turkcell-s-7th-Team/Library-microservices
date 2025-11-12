package com.TurkcellTakim7.category_service.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TurkcellTakim7.category_service.domain.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByName(String name);

    boolean existsByName(String name);
}
