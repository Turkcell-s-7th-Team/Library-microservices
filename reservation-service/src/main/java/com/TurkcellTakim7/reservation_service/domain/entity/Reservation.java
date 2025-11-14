package com.TurkcellTakim7.reservation_service.domain.entity;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

import com.TurkcellTakim7.reservation_service.domain.enums.ReservationStatus;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.PickupWindow;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.ReservationId;

public class Reservation {

    private final ReservationId id;
    private final MemberId memberId;
    private final BookId bookId;

    private int queuePosition;

    private ReservationStatus status;

    private Instant createdAt;
    private Instant updatedAt;

    private PickupWindow pickupWindow;

    private Reservation(ReservationId id,
            MemberId memberId,
            BookId bookId,
            int queuePosition,
            ReservationStatus status,
            Instant createdAt,
            Instant updatedAt,
            PickupWindow pickupWindow) {

        this.id = Objects.requireNonNull(id, "ReservationId cannot be null");
        this.memberId = Objects.requireNonNull(memberId, "MemberId cannot be null");
        this.bookId = Objects.requireNonNull(bookId, "BookId cannot be null");

        if (queuePosition < 1) {
            throw new IllegalArgumentException("queuePosition must be >= 1");
        }
        this.queuePosition = queuePosition;

        this.status = Objects.requireNonNull(status, "ReservationStatus cannot be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt cannot be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt cannot be null");
        this.pickupWindow = pickupWindow;
    }

    public static Reservation createNew(MemberId memberId,
            BookId bookId,
            int queuePosition,
            Clock clock) {

        Instant now = Instant.now(clock);

        return new Reservation(
                ReservationId.generate(),
                memberId,
                bookId,
                queuePosition,
                ReservationStatus.PENDING,
                now,
                now,
                null);
    }

    public static Reservation restore(ReservationId id,
            MemberId memberId,
            BookId bookId,
            int queuePosition,
            ReservationStatus status,
            Instant createdAt,
            Instant updatedAt,
            PickupWindow pickupWindow) {

        return new Reservation(
                id,
                memberId,
                bookId,
                queuePosition,
                status,
                createdAt,
                updatedAt,
                pickupWindow);
    }

    public void markReadyForPickup(Clock clock, Duration pickupDuration) {
        if (status != ReservationStatus.PENDING) {
            throw new IllegalStateException(
                    "Only PENDING reservations can be moved to WAITING_FOR_PICKUP");
        }

        Instant now = Instant.now(clock);
        PickupWindow window = new PickupWindow(
                now,
                now.plus(pickupDuration));

        this.status = ReservationStatus.WAITING_FOR_PICKUP;
        this.pickupWindow = window;
        this.updatedAt = now;
    }

    public void markFulfilled(Clock clock) {
        if (status != ReservationStatus.WAITING_FOR_PICKUP) {
            throw new IllegalStateException(
                    "Only WAITING_FOR_PICKUP reservations can be fulfilled");
        }

        Instant now = Instant.now(clock);
        this.status = ReservationStatus.FULFILLED;
        this.updatedAt = now;
    }

    public void cancel(Clock clock) {
        if (status.isTerminal()) {
            throw new IllegalStateException("Cannot cancel a terminal reservation");
        }

        Instant now = Instant.now(clock);
        this.status = ReservationStatus.CANCELLED;
        this.updatedAt = now;
    }

    public void expireIfPickupWindowExpired(Clock clock) {
        if (status != ReservationStatus.WAITING_FOR_PICKUP || pickupWindow == null) {
            return;
        }

        Instant now = Instant.now(clock);
        if (pickupWindow.isExpiredAt(now)) {
            this.status = ReservationStatus.EXPIRED;
            this.updatedAt = now;
        }
    }

    public boolean isActive() {
        return status.isActive();
    }

    public boolean isForMember(MemberId memberId) {
        return this.memberId.equals(memberId);
    }

    public boolean isForBook(BookId bookId) {
        return this.bookId.equals(bookId);
    }

    public ReservationId getId() {
        return id;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public BookId getBookId() {
        return bookId;
    }

    public int getQueuePosition() {
        return queuePosition;
    }

    public void setQueuePosition(int queuePosition) {
        if (queuePosition < 1) {
            throw new IllegalArgumentException("queuePosition must be >= 1");
        }
        this.queuePosition = queuePosition;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public PickupWindow getPickupWindow() {
        return pickupWindow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Reservation other))
            return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
