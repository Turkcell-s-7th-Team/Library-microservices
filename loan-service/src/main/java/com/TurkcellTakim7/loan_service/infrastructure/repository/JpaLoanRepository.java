package com.TurkcellTakim7.loan_service.infrastructure.repository;

import com.TurkcellTakim7.loan_service.infrastructure.entities.LoanEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface JpaLoanRepository extends JpaRepository<LoanEntity, UUID> {

    List<LoanEntity> findByMemberId(UUID memberId);

    List<LoanEntity> findByBookId(UUID bookId);

    // aktif loan’lar için
    List<LoanEntity> findByMemberIdAndStatus(UUID memberId, String status);

    // gecikmişleri toplamak için
    List<LoanEntity> findByDueDateBeforeAndStatus(LocalDate date, String status);
}
