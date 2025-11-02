package com.TurkcellTakim7.author_service.application.core.Command;

public interface CommandHandler<C extends Command<R>,R>{
    R handle(C command);

}
