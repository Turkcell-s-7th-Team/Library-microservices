package com.TurkcellTakim7.author_service.infrastructure.repository;

import com.TurkcellTakim7.author_service.infrastructure.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaAuthorRepository extends JpaRepository<AuthorEntity, UUID> {

    Optional<AuthorEntity> findByEmail(String email);

    Optional<AuthorEntity> findByPhoneNumber(String phoneNumber);
}
