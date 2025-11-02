package com.TurkcellTakim7.member_service.application.core;

public interface CommandHandler <C extends Command<R>,R>{
  R handle(C command);
}
