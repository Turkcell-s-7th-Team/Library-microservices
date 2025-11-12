package com.TurkcellTakim7.staff_service.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TurkcellTakim7.staff_service.infrastructure.entities.JpaStaffEntity;

public interface SpringDataStaffRepository extends JpaRepository<JpaStaffEntity, UUID> {

    List<JpaStaffEntity> findByNameContaining(String name);

    Optional<JpaStaffEntity> findByPhoneNumber(String phoneNumber);

    public boolean existsByPhone(String phone);

}
