package com.TurkcellTakim7.author_service.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.author_service.domain.entities.Author;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorEmail;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorId;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorPhoneNumber;
import com.TurkcellTakim7.author_service.infrastructure.entities.AuthorEntity;

@Component
public class AuthorEntityMapper {

    public AuthorEntity toEntity(Author author) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(author.getId().value());
        authorEntity.setName(author.getName());
        authorEntity.setSurname(author.getSurname());
        authorEntity.setEmail(author.getEmail().email());
        authorEntity.setPhoneNumber(author.getPhoneNumber().phoneNumber());
        return authorEntity;
    }

    public Author toDomain(AuthorEntity entity) {

        return Author.reconstruct(
                new AuthorId(entity.getId()),
                entity.getName(),
                entity.getSurname(),
                new AuthorEmail(entity.getEmail()),
                new AuthorPhoneNumber(entity.getPhoneNumber()));
    }

}
