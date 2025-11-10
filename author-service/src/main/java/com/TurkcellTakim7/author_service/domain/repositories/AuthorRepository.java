package com.TurkcellTakim7.author_service.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.TurkcellTakim7.author_service.domain.entities.Author;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorEmail;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorId;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorPhoneNumber;

public interface AuthorRepository {

    Author save(Author author);

    Optional<Author> findById(AuthorId id);

    List<Author> getAllAuthors(Integer pageIndex, Integer pageSize);

    void deleteById(AuthorId id);

    Optional<Author> findByEmail(AuthorEmail email);

    Optional<Author> findByPhoneNumber(AuthorPhoneNumber phoneNumber);

    long count();
}
