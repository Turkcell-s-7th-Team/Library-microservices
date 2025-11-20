package com.TurkcellTakim7.fine_service.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;  

import com.TurkcellTakim7.fine_service.infrastructure.entities.JpaFineEntity;

public interface SpringDataFineRepository extends JpaRepository<JpaFineEntity, UUID> {

    List<JpaFineEntity> findAll();

    void deleteById(UUID id);

    
    JpaFineEntity save(JpaFineEntity entity);
 
    Optional<JpaFineEntity> findById(UUID id);

    List<JpaFineEntity> findByMemberId(UUID memberId);

    List<JpaFineEntity> findByMemberIdAndStatus(UUID memberId, String status);

    boolean existsByMemberIdAndStatus(UUID memberId, String status);
}

