package com.TurkcellTakim7.publisher_service.application.commands;

import java.util.UUID;

import com.TurkcellTakim7.publisher_service.application.core.Command;
import com.TurkcellTakim7.publisher_service.application.dto.UpdatedPublisherResponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdatePublisherCommand(
        @NotNull UUID publisherId,
        @NotBlank @Size(min = 2, max = 50) String publisherName,
        @NotBlank @Size(min = 2, max = 50) String address) implements Command<UpdatedPublisherResponse> {

}
