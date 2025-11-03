package com.TurkcellTakim7.author_service.application.mappers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.author_service.application.dtos.AuthorResponse;
import com.TurkcellTakim7.author_service.domain.entities.Author;

@Component
public class AuthorResponseMapper {

    public AuthorResponse toResponse(Author author) {
        return new AuthorResponse(
                author.getId().value(),
                author.getName(),
                author.getSurname(),
                author.getEmail().email(),
                author.getPhoneNumber().phoneNumber());
    }

}
