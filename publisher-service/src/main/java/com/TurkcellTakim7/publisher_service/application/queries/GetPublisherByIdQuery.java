package com.TurkcellTakim7.publisher_service.application.queries;

import java.util.UUID;

import com.TurkcellTakim7.publisher_service.application.core.Query;
import com.TurkcellTakim7.publisher_service.application.dto.PublisherResponse;

import jakarta.validation.constraints.NotNull;

public record GetPublisherByIdQuery(
    @NotNull UUID id) implements Query<PublisherResponse> {

}
