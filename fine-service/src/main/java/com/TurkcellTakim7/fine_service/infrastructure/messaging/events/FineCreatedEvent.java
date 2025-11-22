package com.TurkcellTakim7.fine_service.infrastructure.messaging.events;

import java.util.UUID;

public record FineCreatedEvent(
        UUID memberId) {
}