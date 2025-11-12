package com.TurkcellTakim7.category_service.application.core;

public interface CommandHandler<C extends Command, R> {
    R handle(C command);
}
