package com.TurkcellTakim7.author_service.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.author_service.domain.entities.Author;
import com.TurkcellTakim7.author_service.domain.repositories.AuthorRepository;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorEmail;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorId;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorPhoneNumber;
import com.TurkcellTakim7.author_service.infrastructure.entities.AuthorEntity;
import com.TurkcellTakim7.author_service.infrastructure.mapper.AuthorEntityMapper;
import com.TurkcellTakim7.author_service.infrastructure.repository.JpaAuthorRepository;

@Component
public class AuthorRepositoryAdapter implements AuthorRepository {

    private final JpaAuthorRepository jpaAuthorRepository;
    private final AuthorEntityMapper authorEntityMapper;

    public AuthorRepositoryAdapter(JpaAuthorRepository jpaAuthorRepository, AuthorEntityMapper authorEntityMapper) {
        this.jpaAuthorRepository = jpaAuthorRepository;
        this.authorEntityMapper = authorEntityMapper;
    }

    @Override
    public Author save(Author author) {
        AuthorEntity entity = authorEntityMapper.toEntity(author);
        entity = jpaAuthorRepository.save(entity);
        return authorEntityMapper.toDomain(entity);
    }

    @Override
    public Optional<Author> findById(AuthorId id) {

        return jpaAuthorRepository.findById(id.value())
                .map(authorEntityMapper::toDomain);
    }

    @Override
    public List<Author> getAllAuthors() {
        return jpaAuthorRepository
                .findAll()
                .stream()
                .map(authorEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(AuthorId id) {
        jpaAuthorRepository.deleteById(id.value());
    }

    @Override
    public Optional<Author> findByEmail(AuthorEmail email) {
        return jpaAuthorRepository.findByEmail(email.email())
                .map(authorEntityMapper::toDomain);
    }

    @Override
    public Optional<Author> findByPhoneNumber(AuthorPhoneNumber phoneNumber) {
        return jpaAuthorRepository.findByPhoneNumber(phoneNumber.phoneNumber())
                .map(authorEntityMapper::toDomain);
    }

    @Override
    public long count() {
        return jpaAuthorRepository.count();
    }

}
