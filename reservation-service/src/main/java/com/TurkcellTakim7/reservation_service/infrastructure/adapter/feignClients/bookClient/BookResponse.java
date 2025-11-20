package com.TurkcellTakim7.reservation_service.infrastructure.adapter.feignClients.bookClient;

import java.util.UUID;

public record BookResponse(
        UUID id,
        String title,
        UUID categoryId,
        int availableCopies,
        int copiesCount,
        String isbn,
        UUID publisherId,
        int publishYear) {
}
