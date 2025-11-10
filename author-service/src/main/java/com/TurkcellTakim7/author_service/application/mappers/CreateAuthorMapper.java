package com.TurkcellTakim7.author_service.application.mappers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.author_service.application.commands.CreateAuthorCommand;
import com.TurkcellTakim7.author_service.application.dtos.CreatedAuthorResponse;
import com.TurkcellTakim7.author_service.domain.entities.Author;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorEmail;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorPhoneNumber;

@Component
public class CreateAuthorMapper {

    public Author toDomain(CreateAuthorCommand command) {
        return Author.create(
                command.name(),
                command.surname(),
                new AuthorEmail(command.email()),
                new AuthorPhoneNumber(command.phoneNumber()));
    }

    public CreatedAuthorResponse tResponse(Author author) {
        return new CreatedAuthorResponse(
                author.getId().value(),
                author.getName(),
                author.getSurname());
    }

}
