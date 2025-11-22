package com.TurkcellTakim7.member_service.messaging.events;

import java.util.UUID;

public record FineCreatedEvent(
        UUID memberId) {
}
