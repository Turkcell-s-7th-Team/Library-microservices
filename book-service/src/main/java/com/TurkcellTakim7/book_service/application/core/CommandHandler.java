package com.TurkcellTakim7.book_service.application.core;

public interface CommandHandler<C extends Command<R>, R> {
  R handle(C command);
}
