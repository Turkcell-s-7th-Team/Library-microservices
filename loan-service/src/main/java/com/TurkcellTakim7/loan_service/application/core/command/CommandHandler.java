package com.TurkcellTakim7.loan_service.application.core.command;

public interface CommandHandler<C extends Command<R>, R> {
    R handle(C command);

}
