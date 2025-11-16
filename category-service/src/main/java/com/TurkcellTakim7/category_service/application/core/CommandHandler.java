package com.TurkcellTakim7.category_service.application.core;

public interface CommandHandler<C, R> {
    R handle(C command);
}