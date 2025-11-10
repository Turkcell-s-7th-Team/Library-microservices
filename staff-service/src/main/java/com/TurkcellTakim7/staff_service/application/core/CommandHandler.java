package com.TurkcellTakim7.staff_service.application.core;

public interface CommandHandler<C, R> {
    R handle(C command);
}
