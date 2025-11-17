package com.TurkcellTakim7.reservation_service.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TurkcellTakim7.reservation_service.domain.enums.ReservationStatus;
import com.TurkcellTakim7.reservation_service.infrastructure.entities.ReservationJpaEntity;

@Repository
public interface ReservationJpaRepository extends JpaRepository<ReservationJpaEntity, String> {

        List<ReservationJpaEntity> findByMemberId(String memberId);

        List<ReservationJpaEntity> findByMemberIdAndStatusIn(String memberId,
                        List<ReservationStatus> statuses);

        List<ReservationJpaEntity> findByBookIdOrderByQueuePositionAsc(String bookId);

        Optional<ReservationJpaEntity> findFirstByMemberIdAndBookIdAndStatusIn(String memberId,
                        String bookId,
                        List<ReservationStatus> statuses);
}
