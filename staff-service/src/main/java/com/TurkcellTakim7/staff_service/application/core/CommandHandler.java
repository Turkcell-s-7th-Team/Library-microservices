package com.TurkcellTakim7.staff_service.application.core;

public interface CommandHandler <C extends Command<R>, R> {
    R handle(C command);
}
