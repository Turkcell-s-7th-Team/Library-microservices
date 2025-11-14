package com.TurkcellTakim7.publisher_service.application.commands;

import java.util.UUID;

import com.TurkcellTakim7.publisher_service.application.core.Command;

import jakarta.validation.constraints.NotNull;

public record DeletePublisherCommand(
    @NotNull UUID id) implements Command<Void> {

}
