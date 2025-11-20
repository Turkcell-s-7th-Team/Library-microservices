package com.TurkcellTakim7.fine_service.application.commands;

import java.util.UUID;

import com.TurkcellTakim7.fine_service.application.core.Command;

import jakarta.validation.constraints.NotNull;

public record DeleteFineCommand(
        @NotNull UUID fineId
) implements Command<Void> {}
