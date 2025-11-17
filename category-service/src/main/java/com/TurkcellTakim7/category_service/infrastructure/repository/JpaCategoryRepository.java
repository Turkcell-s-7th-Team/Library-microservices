package com.TurkcellTakim7.category_service.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TurkcellTakim7.category_service.infrastructure.entities.CategoryEntity;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, String> {
    Optional<CategoryEntity> findByName(String name);

    boolean existsByName(String name);
}
