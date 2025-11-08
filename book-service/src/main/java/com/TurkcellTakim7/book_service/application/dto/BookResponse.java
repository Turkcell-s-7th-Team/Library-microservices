package com.TurkcellTakim7.book_service.application.dto;

import java.util.UUID;

public record BookResponse(
    UUID id, String title, UUID categoryId, int availableCopies, int copiesCount,
    String isbn, UUID publisherId, int publishYear) {

}
