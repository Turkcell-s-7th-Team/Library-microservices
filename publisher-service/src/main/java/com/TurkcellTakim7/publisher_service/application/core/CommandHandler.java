package com.TurkcellTakim7.publisher_service.application.core;

public interface CommandHandler<C extends Command<R>, R> {
  R handle(C command);
}
