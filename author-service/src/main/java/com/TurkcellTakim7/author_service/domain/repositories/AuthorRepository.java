package com.TurkcellTakim7.author_service.domain.repositories;

import com.TurkcellTakim7.author_service.domain.entities.Author;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorId;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorEmail;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorPhoneNumber;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Author save(Author author);

    Optional<Author> findById(AuthorId id);

    List<Author> getAllAuthors();

    void deleteById(AuthorId id);

    Optional<Author> findByEmail(AuthorEmail email);

    Optional<Author> findByPhoneNumber(AuthorPhoneNumber phoneNumber);

    long count();
}
