package com.TurkcellTakim7.publisher_service.application.commands;

import com.TurkcellTakim7.publisher_service.application.core.Command;
import com.TurkcellTakim7.publisher_service.application.dto.CreatedPublisherResponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePublisherCommand(
    @NotBlank @Size(min = 2, max = 50) String publisherName,
    @NotBlank @Size(min = 2, max = 50) String address)
    implements Command<CreatedPublisherResponse> {
}
