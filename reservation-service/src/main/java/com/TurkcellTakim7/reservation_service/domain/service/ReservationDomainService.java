package com.TurkcellTakim7.reservation_service.domain.service;

import java.time.Clock;
import java.time.Duration;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;
import com.TurkcellTakim7.reservation_service.domain.enums.ReservationStatus;
import com.TurkcellTakim7.reservation_service.domain.exceptions.ActiveReservationExistsException;
import com.TurkcellTakim7.reservation_service.domain.exceptions.ReservationNotFoundException;
import com.TurkcellTakim7.reservation_service.domain.exceptions.ReservationValidationException;
import com.TurkcellTakim7.reservation_service.domain.repository.ReservationRepository;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.ReservationId;

@Service
public class ReservationDomainService {

    private static final Duration DEFAULT_PICKUP_DURATION = Duration.ofDays(2);

    private final ReservationRepository reservationRepository;

    public ReservationDomainService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(String memberIdRaw, String bookIdRaw) {

        validateRawIds(memberIdRaw, bookIdRaw);

        MemberId memberId = MemberId.of(memberIdRaw);
        BookId bookId = BookId.of(bookIdRaw);

        List<ReservationStatus> activeStatuses = new ArrayList<>(
                EnumSet.of(ReservationStatus.PENDING, ReservationStatus.WAITING_FOR_PICKUP));

        Optional<Reservation> existingActive = reservationRepository.findFirstByMemberIdAndBookIdAndStatusIn(
                memberId,
                bookId,
                activeStatuses);

        if (existingActive.isPresent()) {
            throw new ActiveReservationExistsException(memberId.value(), bookId.value());
        }

        List<Reservation> queue = reservationRepository.findByBookIdOrderByQueuePositionAsc(bookId);
        int nextPosition = queue.isEmpty()
                ? 1
                : queue.get(queue.size() - 1).getQueuePosition() + 1;

        // 🔍 Eskiden: Reservation.createNew(..., clock)
        Clock clock = Clock.systemUTC();
        Reservation reservation = Reservation.createNew(
                memberId,
                bookId,
                nextPosition,
                clock);

        return reservationRepository.save(reservation);
    }

    public Reservation getReservationById(String reservationIdRaw) {
        if (reservationIdRaw == null || reservationIdRaw.isBlank()) {
            throw new ReservationValidationException("reservationId cannot be null or blank");
        }

        ReservationId reservationId = ReservationId.of(reservationIdRaw);
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId.value()));
    }

    public List<Reservation> getReservationsByMemberId(String memberIdRaw) {
        if (memberIdRaw == null || memberIdRaw.isBlank()) {
            throw new ReservationValidationException("memberId cannot be null or blank");
        }

        MemberId memberId = MemberId.of(memberIdRaw);
        return reservationRepository.findByMemberId(memberId);
    }

    public void cancelReservation(String reservationIdRaw) {
        Reservation reservation = getReservationById(reservationIdRaw);

        Clock clock = Clock.systemUTC();
        reservation.cancel(clock);

        reservationRepository.save(reservation);
    }

    public void fulfillReservation(String reservationIdRaw) {
        Reservation reservation = getReservationById(reservationIdRaw);

        Clock clock = Clock.systemUTC();
        reservation.markFulfilled(clock);

        reservationRepository.save(reservation);
    }

    public Reservation markNextReservationReadyForPickup(String bookIdRaw) {
        if (bookIdRaw == null || bookIdRaw.isBlank()) {
            throw new ReservationValidationException("bookId cannot be null or blank");
        }

        BookId bookId = BookId.of(bookIdRaw);

        List<Reservation> queue = reservationRepository.findByBookIdOrderByQueuePositionAsc(bookId);

        Optional<Reservation> nextOpt = queue.stream()
                .filter(r -> r.getStatus() == ReservationStatus.PENDING)
                .findFirst();

        if (nextOpt.isEmpty()) {
            return null;
        }

        Reservation reservation = nextOpt.get();

        Clock clock = Clock.systemUTC();
        reservation.markReadyForPickup(clock, DEFAULT_PICKUP_DURATION);

        return reservationRepository.save(reservation);
    }

    public void deleteReservation(String reservationIdRaw) {
        if (reservationIdRaw == null || reservationIdRaw.isBlank()) {
            throw new ReservationValidationException("reservationId cannot be null or blank");
        }

        ReservationId reservationId = ReservationId.of(reservationIdRaw);

        if (!reservationRepository.existsById(reservationId)) {
            throw new ReservationNotFoundException(reservationId.value());
        }

        reservationRepository.deleteById(reservationId);
    }

    private void validateRawIds(String memberIdRaw, String bookIdRaw) {
        if (memberIdRaw == null || memberIdRaw.isBlank()) {
            throw new ReservationValidationException("memberId cannot be null or blank");
        }
        if (bookIdRaw == null || bookIdRaw.isBlank()) {
            throw new ReservationValidationException("bookId cannot be null or blank");
        }
    }
}
