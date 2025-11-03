package com.TurkcellTakim7.author_service.application.commandHandlers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.author_service.application.commands.UpdateAuthorCommand;
import com.TurkcellTakim7.author_service.application.core.Command.CommandHandler;
import com.TurkcellTakim7.author_service.application.dtos.AuthorResponse;
import com.TurkcellTakim7.author_service.domain.entities.Author;
import com.TurkcellTakim7.author_service.domain.repositories.AuthorRepository;
import com.TurkcellTakim7.author_service.domain.services.AuthorDomainService;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorEmail;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorId;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorPhoneNumber;

@Component
public class UpdateAuthorCommandHandler implements CommandHandler<UpdateAuthorCommand, AuthorResponse> {

    private final AuthorDomainService authorDomainService;
    private final AuthorRepository authorRepository;

    public UpdateAuthorCommandHandler(AuthorDomainService authorDomainService,
            AuthorRepository authorRepository) {
        this.authorDomainService = authorDomainService;
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorResponse handle(UpdateAuthorCommand command) {

        Author updatedAuthor = authorDomainService.updateAuthor(
                new AuthorId(command.id()),
                command.name(),
                command.surname(),
                new AuthorEmail(command.email()),
                new AuthorPhoneNumber(command.phoneNumber()));

        // domain tarafında sadece entity güncellendi, şimdi repo ile persist et
        authorRepository.save(updatedAuthor);

        return new AuthorResponse(
                updatedAuthor.getId().value(),
                updatedAuthor.getName(),
                updatedAuthor.getSurname(),
                updatedAuthor.getEmail().email(),
                updatedAuthor.getPhoneNumber().phoneNumber());
    }
}
