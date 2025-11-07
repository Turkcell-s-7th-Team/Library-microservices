package com.TurkcellTakim7.book_service.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TurkcellTakim7.book_service.infrastructure.entities.JpaBookEntity;

public interface SpringDataBookRepository extends JpaRepository<JpaBookEntity, UUID> {

  Optional<JpaBookEntity> findByIsbn(String isbn);

  boolean existsByIdAndAvailableCopiesGreaterThan(UUID id, int availableCopies);

  boolean existsByIsbn(String isbn);

  List<JpaBookEntity> findAllByCategoryId(UUID categoryId);

  List<JpaBookEntity> findAllByPublisherId(UUID publisherId);
}
