package com.TurkcellTakim7.reservation_service.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TurkcellTakim7.reservation_service.domain.enums.ReservationStatus;
import com.TurkcellTakim7.reservation_service.infrastructure.entities.ReservationJpaEntity;

@Repository
public interface ReservationJpaRepository extends JpaRepository<ReservationJpaEntity, UUID> {

    List<ReservationJpaEntity> findByMemberId(UUID memberId);

    List<ReservationJpaEntity> findByMemberIdAndStatusIn(UUID memberId,
                                                         List<ReservationStatus> statuses);

    List<ReservationJpaEntity> findByBookIdOrderByQueuePositionAsc(UUID bookId);

    Optional<ReservationJpaEntity> findFirstByMemberIdAndBookIdAndStatusIn(
            UUID memberId,
            UUID bookId,
            List<ReservationStatus> statuses);
}
