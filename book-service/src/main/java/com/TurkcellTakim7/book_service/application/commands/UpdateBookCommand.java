package com.TurkcellTakim7.book_service.application.commands;

import java.util.UUID;

import com.TurkcellTakim7.book_service.application.core.Command;
import com.TurkcellTakim7.book_service.application.dto.UpdatedBookResponse;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateBookCommand(
        @NotNull UUID bookId,
        @NotBlank @Size(min = 1, max = 255) String title,
        @NotNull @Min(0) Integer availableCopies,
        @NotNull @Min(1) Integer copiesCount,
        @NotNull UUID categoryId,
        @NotNull UUID publisherId,
        @NotNull @Min(1) Integer publishYear) implements Command<UpdatedBookResponse> {
}
