package com.TurkcellTakim7.book_service.application.dto;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateBookRequest(
    @NotBlank @Size(min = 1, max = 255) String title,
    @NotNull @Min(0) Integer availableCopies,
    @NotNull @Min(1) Integer copiesCount,
    @NotNull UUID categoryId,
    @NotNull UUID publisherId,
    @NotNull @Min(1) Integer publishYear) {
}
