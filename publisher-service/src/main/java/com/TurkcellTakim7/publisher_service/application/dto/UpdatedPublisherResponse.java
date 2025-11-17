package com.TurkcellTakim7.publisher_service.application.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdatedPublisherResponse(
        @NotNull UUID publisherId,
        @NotBlank @Size(min = 2, max = 50) String publisherName,
        @NotBlank @Size(min = 2, max = 50) String address) {
}
