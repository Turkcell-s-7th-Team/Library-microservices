package com.TurkcellTakim7.book_service.application.queries;

import java.util.UUID;

import com.TurkcellTakim7.book_service.application.core.Query;
import com.TurkcellTakim7.book_service.application.dto.BookResponse;

import jakarta.validation.constraints.NotNull;

public record GetBookByIdQuery(
    @NotNull UUID id) implements Query<BookResponse> {

}
