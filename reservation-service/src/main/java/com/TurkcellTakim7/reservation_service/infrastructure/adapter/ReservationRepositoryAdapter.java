package com.TurkcellTakim7.reservation_service.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;
import com.TurkcellTakim7.reservation_service.domain.enums.ReservationStatus;
import com.TurkcellTakim7.reservation_service.domain.repository.ReservationRepository;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.ReservationId;
import com.TurkcellTakim7.reservation_service.infrastructure.entities.ReservationJpaEntity;
import com.TurkcellTakim7.reservation_service.infrastructure.mapper.ReservationDataMapper;
import com.TurkcellTakim7.reservation_service.infrastructure.repository.ReservationJpaRepository;

@Repository
public class ReservationRepositoryAdapter implements ReservationRepository {

    private final ReservationJpaRepository jpaRepository;
    private final ReservationDataMapper mapper;

    public ReservationRepositoryAdapter(ReservationJpaRepository jpaRepository,
            ReservationDataMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationJpaEntity entity = mapper.toEntity(reservation);
        ReservationJpaEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Reservation> findById(ReservationId id) {
        return jpaRepository.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public void deleteById(ReservationId id) {
        jpaRepository.deleteById(id.value());
    }

    @Override
    public boolean existsById(ReservationId id) {
        return jpaRepository.existsById(id.value());
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public List<Reservation> findByMemberId(MemberId memberId) {
        return jpaRepository.findByMemberId(memberId.value())
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Reservation> findByMemberIdAndStatusIn(MemberId memberId,
            List<ReservationStatus> statuses) {
        return jpaRepository.findByMemberIdAndStatusIn(memberId.value(), statuses)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Reservation> findByBookIdOrderByQueuePositionAsc(BookId bookId) {
        return jpaRepository.findByBookIdOrderByQueuePositionAsc(bookId.value())
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Reservation> findFirstByMemberIdAndBookIdAndStatusIn(
            MemberId memberId,
            BookId bookId,
            List<ReservationStatus> statuses) {
        return jpaRepository
                .findFirstByMemberIdAndBookIdAndStatusIn(
                        memberId.value(),
                        bookId.value(),
                        statuses)
                .map(mapper::toDomain);
    }
}
