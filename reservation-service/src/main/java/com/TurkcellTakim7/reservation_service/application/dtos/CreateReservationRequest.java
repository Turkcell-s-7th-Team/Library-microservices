package com.TurkcellTakim7.reservation_service.application.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class CreateReservationRequest {

    @NotBlank
    @Schema(description = "Rezervasyonu yapan üyenin ID'si", example = "f3a0f0c8-1234-5678-9abc-def012345678")
    private String memberId;

    @NotBlank
    @Schema(description = "Rezervasyon yapılacak kitabın ID'si", example = "c7b28f3d-9876-5432-1abc-def098765432")
    private String bookId;

    public CreateReservationRequest() {
    }

    public CreateReservationRequest(String memberId, String bookId) {
        this.memberId = memberId;
        this.bookId = bookId;
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
}
