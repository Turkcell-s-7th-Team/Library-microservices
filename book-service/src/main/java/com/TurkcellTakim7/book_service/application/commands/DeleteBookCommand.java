package com.TurkcellTakim7.book_service.application.commands;

import java.util.UUID;

import com.TurkcellTakim7.book_service.application.core.Command;

import jakarta.validation.constraints.NotNull;

public record DeleteBookCommand(
    @NotNull UUID id) implements Command<Void> {

}
