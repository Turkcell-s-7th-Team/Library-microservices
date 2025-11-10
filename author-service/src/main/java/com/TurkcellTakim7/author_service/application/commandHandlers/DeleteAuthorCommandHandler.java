package com.TurkcellTakim7.author_service.application.commandHandlers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.author_service.application.commands.DeleteAuthorCommand;
import com.TurkcellTakim7.author_service.application.core.Command.CommandHandler;
import com.TurkcellTakim7.author_service.domain.repositories.AuthorRepository;
import com.TurkcellTakim7.author_service.domain.services.AuthorDomainService;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorId;

@Component
public class DeleteAuthorCommandHandler implements CommandHandler<DeleteAuthorCommand, Void> {

    private final AuthorDomainService authorDomainService;
    private final AuthorRepository authorRepository;

    public DeleteAuthorCommandHandler(AuthorDomainService authorDomainService,
            AuthorRepository authorRepository) {
        this.authorDomainService = authorDomainService;
        this.authorRepository = authorRepository;
    }

    @Override
    public Void handle(DeleteAuthorCommand command) {
        authorDomainService.deleteAuthor(new AuthorId(command.id()));
        authorRepository.deleteById(new AuthorId(command.id()));
        return null;
    }
}
