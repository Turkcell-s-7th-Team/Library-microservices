package com.TurkcellTakim7.publisher_service.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TurkcellTakim7.publisher_service.domain.entities.Publisher;
import com.TurkcellTakim7.publisher_service.infrastructure.entities.JpaPublisherEntity;

public interface SpringDataPublisherRepository extends JpaRepository<JpaPublisherEntity, UUID> {

  boolean existsByPublisherName(String name);

  Optional<Publisher> findByPublisherName(String name);

}
