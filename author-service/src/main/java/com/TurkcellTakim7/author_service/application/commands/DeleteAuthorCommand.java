package com.TurkcellTakim7.author_service.application.commands;

import java.util.UUID;

import com.TurkcellTakim7.author_service.application.core.Command.Command;

public record DeleteAuthorCommand(UUID id) implements Command<Void> {
}
