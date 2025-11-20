package com.TurkcellTakim7.reservation_service.application.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public class CancelReservationRequest {

    @Schema(description = "İptal sebebi (opsiyonel)", example = "Kullanıcı gelmekten vazgeçti")
    private String reason;

    public CancelReservationRequest() {
    }

    public CancelReservationRequest(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
