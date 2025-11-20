package com.TurkcellTakim7.reservation_service.application.dtos;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class CreateReservationRequest {

    @NotNull
    @Schema(description = "Rezervasyonu yapan üyenin ID'si", example = "f3a0f0c8-1234-5678-9abc-def012345678")
    private UUID memberId;

    @NotNull
    @Schema(description = "Rezervasyon yapılacak kitabın ID'si", example = "c7b28f3d-9876-5432-1abc-def098765432")
    private UUID bookId;

    public CreateReservationRequest() {
    }

    public CreateReservationRequest(UUID memberId, UUID bookId) {
        this.memberId = memberId;
        this.bookId = bookId;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }
}
