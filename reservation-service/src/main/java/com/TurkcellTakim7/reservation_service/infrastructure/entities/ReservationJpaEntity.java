package com.TurkcellTakim7.reservation_service.infrastructure.entities;

import java.time.Instant;

import com.TurkcellTakim7.reservation_service.domain.enums.ReservationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class ReservationJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false, length = 36)
    private String id;

    @Column(name = "member_id", nullable = false, length = 36)
    private String memberId;

    @Column(name = "book_id", nullable = false, length = 36)
    private String bookId;

    @Column(name = "queue_position", nullable = false)
    private int queuePosition;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private ReservationStatus status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "pickup_start_at")
    private Instant pickupStartAt;

    @Column(name = "pickup_expires_at")
    private Instant pickupExpiresAt;

    // ==== Constructors ====

    public ReservationJpaEntity() {
    }

    public ReservationJpaEntity(String id,
                                String memberId,
                                String bookId,
                                int queuePosition,
                                ReservationStatus status,
                                Instant createdAt,
                                Instant updatedAt,
                                Instant pickupStartAt,
                                Instant pickupExpiresAt) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.queuePosition = queuePosition;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.pickupStartAt = pickupStartAt;
        this.pickupExpiresAt = pickupExpiresAt;
    }

    // ==== Getters & Setters ====

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQueuePosition() {
        return queuePosition;
    }

    public void setQueuePosition(int queuePosition) {
        this.queuePosition = queuePosition;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getPickupStartAt() {
        return pickupStartAt;
    }

    public void setPickupStartAt(Instant pickupStartAt) {
        this.pickupStartAt = pickupStartAt;
    }

    public Instant getPickupExpiresAt() {
        return pickupExpiresAt;
    }

    public void setPickupExpiresAt(Instant pickupExpiresAt) {
        this.pickupExpiresAt = pickupExpiresAt;
    }
}
