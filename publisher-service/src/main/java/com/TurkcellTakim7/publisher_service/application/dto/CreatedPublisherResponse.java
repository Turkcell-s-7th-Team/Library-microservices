package com.TurkcellTakim7.publisher_service.application.dto;

import java.util.UUID;

public record CreatedPublisherResponse(
    UUID id,
    String publisherName,
    String address) {
}
