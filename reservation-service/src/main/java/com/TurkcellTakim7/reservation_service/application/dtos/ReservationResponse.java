package com.TurkcellTakim7.reservation_service.application.dtos;

import java.time.Instant;

import com.TurkcellTakim7.reservation_service.domain.enums.ReservationStatus;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReservationResponse {

    @Schema(description = "Rezervasyon ID'si", example = "a1b2c3d4-1111-2222-3333-444455556666")
    private String id;

    @Schema(description = "Üye ID'si", example = "f3a0f0c8-1234-5678-9abc-def012345678")
    private String memberId;

    @Schema(description = "Kitap ID'si", example = "c7b28f3d-9876-5432-1abc-def098765432")
    private String bookId;

    @Schema(description = "Aynı kitap için rezervasyon kuyruğundaki pozisyon (1,2,3,...)", example = "1")
    private int queuePosition;

    @Schema(description = "Rezervasyon durumu", example = "PENDING")
    private ReservationStatus status;

    @Schema(description = "Rezervasyon oluşturulma zamanı (UTC)", example = "2025-11-14T10:15:30Z")
    private Instant createdAt;

    @Schema(description = "Rezervasyonun son güncellenme zamanı (UTC)", example = "2025-11-14T10:20:00Z")
    private Instant updatedAt;

    @Schema(description = "Kitabın bu kullanıcı için ayrılmaya başlandığı zaman (WAITING_FOR_PICKUP durumunda)", example = "2025-11-15T09:00:00Z")
    private Instant pickupStartAt;

    @Schema(description = "Kullanıcının kitabı alması gereken son zaman", example = "2025-11-17T09:00:00Z")
    private Instant pickupExpiresAt;

    public ReservationResponse() {
    }

    public ReservationResponse(String id,
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
