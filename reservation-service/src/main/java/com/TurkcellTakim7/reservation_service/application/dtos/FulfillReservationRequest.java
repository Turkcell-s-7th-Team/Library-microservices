package com.TurkcellTakim7.reservation_service.application.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rezervasyonun FULFILLED yapılması (kitap teslim alındı) için istek DTO'su.
 * İleride staffId vs. eklenebilir.
 * Şimdilik boş, Swagger dokümantasyonu için duruyor.
 */
public class FulfillReservationRequest {

    @Schema(description = "Kitabı teslim eden personel ID'si (opsiyonel, şu an kullanılmıyor)", example = "staff-123", nullable = true)
    private String staffId;

    public FulfillReservationRequest() {
    }

    public FulfillReservationRequest(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
