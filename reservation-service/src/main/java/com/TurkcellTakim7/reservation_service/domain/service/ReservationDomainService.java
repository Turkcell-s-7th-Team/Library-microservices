package com.TurkcellTakim7.reservation_service.domain.service;

import java.time.Clock;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;
import com.TurkcellTakim7.reservation_service.domain.enums.ReservationStatus;
import com.TurkcellTakim7.reservation_service.domain.exceptions.ActiveReservationExistsException;
import com.TurkcellTakim7.reservation_service.domain.exceptions.ReservationNotFoundException;
import com.TurkcellTakim7.reservation_service.domain.exceptions.ReservationValidationException;
import com.TurkcellTakim7.reservation_service.domain.repository.ReservationRepository;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.ReservationId;

public class ReservationDomainService {

    private static final Duration DEFAULT_PICKUP_DURATION = Duration.ofDays(2);

    private final ReservationRepository reservationRepository;

    public ReservationDomainService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(MemberId memberId, BookId bookId) {

        if (memberId == null) {
            throw new ReservationValidationException("memberId cannot be null");
        }
        if (bookId == null) {
            throw new ReservationValidationException("bookId cannot be null");
        }

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

        Clock clock = Clock.systemUTC();
        Reservation reservation = Reservation.createNew(
                memberId,
                bookId,
                nextPosition,
                clock);

        return reservationRepository.save(reservation);
    }

    public Reservation getReservationById(ReservationId reservationId) {
        if (reservationId == null) {
            throw new ReservationValidationException("reservationId cannot be null");
        }

        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId.value()));
    }

    public List<Reservation> getReservationsByMemberId(MemberId memberId) {
        if (memberId == null) {
            throw new ReservationValidationException("memberId cannot be null");
        }

        return reservationRepository.findByMemberId(memberId);
    }

    public void cancelReservation(ReservationId reservationId) {
        Reservation reservation = getReservationById(reservationId);

        Clock clock = Clock.systemUTC();
        reservation.cancel(clock);

        reservationRepository.save(reservation);
    }

    public void fulfillReservation(ReservationId reservationId) {
        Reservation reservation = getReservationById(reservationId);

        Clock clock = Clock.systemUTC();
        reservation.markFulfilled(clock);

        reservationRepository.save(reservation);
    }

    /**
     * Eski metod: sadece sıradaki PENDING rezervasyonu WAITING_FOR_PICKUP yapar.
     */
    public Reservation markNextReservationReadyForPickup(BookId bookId) {
        if (bookId == null) {
            throw new ReservationValidationException("bookId cannot be null");
        }

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

    /**
     * Yeni metod:
     * 1) sıradaki PENDING rezervasyonu WAITING_FOR_PICKUP yapar,
     * 2) aktif kuyruğun (PENDING + WAITING_FOR_PICKUP) queuePosition'larını 1,2,3... olarak sıkıştırır.
     */
    public Reservation markNextReservationReadyForPickupAndReorder(BookId bookId) {
        Reservation reservation = markNextReservationReadyForPickup(bookId);

        if (reservation == null) {
            return null;
        }

        reorderActiveQueueForBook(bookId);

        return reservation;
    }

    /**
     * Aktif (isActive == true) rezervasyonların queuePosition'unu
     * 1,2,3,... şeklinde yeniden numaralandırır.
     */
    private void reorderActiveQueueForBook(BookId bookId) {
        List<Reservation> queue = reservationRepository.findByBookIdOrderByQueuePositionAsc(bookId);

        List<Reservation> active = queue.stream()
                .filter(Reservation::isActive)
                .sorted(Comparator.comparingInt(Reservation::getQueuePosition))
                .toList();

        int position = 1;
        for (Reservation r : active) {
            r.setQueuePosition(position++);
            reservationRepository.save(r);
        }
    }

    public void deleteReservation(ReservationId reservationId) {
        if (reservationId == null) {
            throw new ReservationValidationException("reservationId cannot be null");
        }

        if (!reservationRepository.existsById(reservationId)) {
            throw new ReservationNotFoundException(reservationId.value());
        }

        reservationRepository.deleteById(reservationId);
    }
}
