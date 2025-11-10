package com.TurkcellTakim7.author_service.application.commandHandlers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.author_service.application.commands.CreateAuthorCommand;
import com.TurkcellTakim7.author_service.application.core.Command.CommandHandler;
import com.TurkcellTakim7.author_service.application.dtos.CreatedAuthorResponse;
import com.TurkcellTakim7.author_service.application.mappers.CreateAuthorMapper;
import com.TurkcellTakim7.author_service.domain.entities.Author;
import com.TurkcellTakim7.author_service.domain.repositories.AuthorRepository;

@Component
public class CreateAuthorCommandHandler implements CommandHandler<CreateAuthorCommand, CreatedAuthorResponse> {

    private final AuthorRepository authorRepository;
    private final CreateAuthorMapper createAuthorMapper;

    public CreateAuthorCommandHandler(AuthorRepository authorRepository, CreateAuthorMapper createAuthorMapper) {
        this.authorRepository = authorRepository;
        this.createAuthorMapper = createAuthorMapper;
    }

    @Override
    public CreatedAuthorResponse handle(CreateAuthorCommand command) {
        Author author = createAuthorMapper.toDomain(command);
        author = authorRepository.save(author);

        return createAuthorMapper.tResponse(author);
    }

}
