package com.TurkcellTakim7.reservation_service.domain.repository;

import java.util.List;
import java.util.Optional;

import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;
import com.TurkcellTakim7.reservation_service.domain.enums.ReservationStatus;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.ReservationId;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    Optional<Reservation> findById(ReservationId id);

    void deleteById(ReservationId id);

    boolean existsById(ReservationId id);

    long count();

    List<Reservation> findByMemberId(MemberId memberId);

    List<Reservation> findByMemberIdAndStatusIn(MemberId memberId,
            List<ReservationStatus> statuses);

    List<Reservation> findByBookIdOrderByQueuePositionAsc(BookId bookId);

    Optional<Reservation> findFirstByMemberIdAndBookIdAndStatusIn(MemberId memberId,
            BookId bookId,
            List<ReservationStatus> statuses);
}
