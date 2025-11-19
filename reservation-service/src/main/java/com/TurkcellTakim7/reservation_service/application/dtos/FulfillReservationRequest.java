package com.TurkcellTakim7.reservation_service.application.dtos;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rezervasyonun FULFILLED yapılması (kitap teslim alındı) için istek DTO'su.
 * İleride staffId vs. eklenebilir.
 */
public class FulfillReservationRequest {

    @Schema(description = "Kitabı teslim eden personel ID'si (opsiyonel, şu an kullanılmıyor)", example = "11111111-2222-3333-4444-555555555555", nullable = true)
    private UUID staffId;

    public FulfillReservationRequest() {
    }

    public FulfillReservationRequest(UUID staffId) {
        this.staffId = staffId;
    }

    public UUID getStaffId() {
        return staffId;
    }

    public void setStaffId(UUID staffId) {
        this.staffId = staffId;
    }
}
