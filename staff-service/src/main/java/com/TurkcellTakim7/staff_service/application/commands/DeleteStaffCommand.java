package com.TurkcellTakim7.staff_service.application.commands;

import java.util.UUID;

import com.TurkcellTakim7.staff_service.application.core.Command;

import jakarta.validation.constraints.NotNull;

public record DeleteStaffCommand(
    @NotNull UUID staffId
) implements Command<Void> {}
