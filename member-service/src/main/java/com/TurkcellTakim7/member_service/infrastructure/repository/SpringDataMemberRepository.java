package com.TurkcellTakim7.member_service.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TurkcellTakim7.member_service.infrastructure.entities.JpaMemberEntity;

public interface SpringDataMemberRepository extends JpaRepository<JpaMemberEntity, UUID> {

  boolean existsByEmail(String email);

  List<JpaMemberEntity> findByNameContaining(String name);

  List<JpaMemberEntity> findByMembershipLevel(String membershipLevel);

  Optional<JpaMemberEntity> findByEmail(String email);

}
