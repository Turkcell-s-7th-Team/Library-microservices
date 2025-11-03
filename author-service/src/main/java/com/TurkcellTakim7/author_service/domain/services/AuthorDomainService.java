package com.TurkcellTakim7.author_service.domain.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.author_service.domain.entities.Author;
import com.TurkcellTakim7.author_service.domain.exceptions.AuthorAlreadyExistsException;
import com.TurkcellTakim7.author_service.domain.exceptions.AuthorNotFoundException;
import com.TurkcellTakim7.author_service.domain.exceptions.AuthorValidationException;
import com.TurkcellTakim7.author_service.domain.exceptions.InvalidAuthorException;
import com.TurkcellTakim7.author_service.domain.repositories.AuthorRepository;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorEmail;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorId;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorPhoneNumber;

@Component
public class AuthorDomainService {

    private final AuthorRepository authorRepository;

    public AuthorDomainService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * Yeni bir Author oluşturur ve email ile telefon benzersizliğini kontrol eder.
     */
    public Author createAuthor(String name, String surname, AuthorEmail email, AuthorPhoneNumber phoneNumber) {

        if (isEmailAlreadyExists(email)) {
            throw new AuthorAlreadyExistsException(email);
        }

        if (isPhoneNumberAlreadyExists(phoneNumber)) {
            throw new AuthorAlreadyExistsException(phoneNumber);
        }

        // Alan doğrulamaları
        if (name == null || name.isBlank())
            throw new AuthorValidationException("name", "cannot be null or empty");
        if (surname == null || surname.isBlank())
            throw new AuthorValidationException("surname", "cannot be null or empty");

        try {
            return new Author(
                    AuthorId.generateAuthorId(),
                    name,
                    surname,
                    email,
                    phoneNumber);
        } catch (Exception ex) {
            throw new InvalidAuthorException("Failed to create Author", ex);
        }
    }

    public Author getAuthor(AuthorId id) {
        Author existingAuthor = authorRepository
                .findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        return existingAuthor;
    }

    public List<Author> getAuthorList(Integer pageIndex, Integer pageSize) {
        return authorRepository.getAllAuthors(pageIndex, pageSize);
    }

    /**
     * Author bilgilerini günceller.
     * 
     * public Author updateAuthor(AuthorId authorId, String name, String surname,
     * AuthorEmail email,
     * AuthorPhoneNumber phoneNumber) {
     * Author existingAuthor = authorRepository.findById(authorId)
     * .orElseThrow(() -> new InvalidAuthorException("Author not found: " +
     * authorId.value()));
     * 
     * if (!existingAuthor.getEmail().equals(email) && isEmailAlreadyExists(email))
     * {
     * throw new AuthorAlreadyExistsException(email);
     * }
     * 
     * if (!existingAuthor.getPhoneNumber().equals(phoneNumber) &&
     * isPhoneNumberAlreadyExists(phoneNumber)) {
     * throw new AuthorAlreadyExistsException(phoneNumber);
     * }
     * 
     * existingAuthor.updatePersonalInfo(name, surname, email, phoneNumber);
     * return existingAuthor;
     * }
     */

    // === Yardımcı kontroller ===
    private boolean isEmailAlreadyExists(AuthorEmail email) {
        return authorRepository.findByEmail(email).isPresent();
    }

    private boolean isPhoneNumberAlreadyExists(AuthorPhoneNumber phoneNumber) {
        return authorRepository.findByPhoneNumber(phoneNumber).isPresent();
    }
}
