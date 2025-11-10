package com.TurkcellTakim7.author_service.domain.entities;

import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorId;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorEmail;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorPhoneNumber;

import java.util.Objects;

public class Author {

    private final AuthorId id;
    private String name;
    private String surname;
    private AuthorEmail email;
    private AuthorPhoneNumber phoneNumber;

    protected Author() {
        this.id = null;
    }

    public Author(String name, String surname, String email, String phoneNumber) {
        this.id = AuthorId.generateAuthorId();
        this.setName(name);
        this.setSurname(surname);
        this.setEmail(email != null ? new AuthorEmail(email) : null);
        this.setPhoneNumber(phoneNumber != null ? new AuthorPhoneNumber(phoneNumber) : null);
    }

    public Author(AuthorId id, String name, String surname, AuthorEmail email,
            AuthorPhoneNumber phoneNumber) {
        this.id = Objects.requireNonNull(id, "Author ID cannot be null");
        this.setName(name);
        this.setSurname(surname);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static Author create(String name, String surname, AuthorEmail email, AuthorPhoneNumber phoneNumber){

        AuthorId authorId = AuthorId.generateAuthorId();
        return new Author(authorId, name, surname, email, phoneNumber);
    }

    public static Author reconstruct(AuthorId id, String name, String surname, AuthorEmail email, AuthorPhoneNumber phoneNumber){

        return new Author(id, name, surname, email, phoneNumber);
    }

    public AuthorId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFullName() {
        return (name != null ? name : "") + " " + (surname != null ? surname : "").trim();
    }

    public AuthorEmail getEmail() {
        return email;
    }

    public String getEmailValue() {
        return email != null ? email.email() : null;
    }

    public AuthorPhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public String getPhoneNumberValue() {
        return phoneNumber != null ? phoneNumber.phoneNumber() : null;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
    }

    public void setSurname(String surname) {
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Surname cannot be null or empty");
        }
        this.surname = surname.trim();
    }

    public void setEmail(AuthorEmail email) {
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email != null && !email.trim().isEmpty() ? new AuthorEmail(email) : null;
    }

    public void setPhoneNumber(AuthorPhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber != null && !phoneNumber.trim().isEmpty()
                ? new AuthorPhoneNumber(phoneNumber)
                : null;
    }

    public void updateEmail(AuthorEmail newEmail) {
        this.setEmail(newEmail);
    }

    public void updateEmail(String newEmail) {
        this.setEmail(newEmail);
    }

    public void updatePhoneNumber(AuthorPhoneNumber newPhoneNumber) {
        this.setPhoneNumber(newPhoneNumber);
    }

    public void updatePhoneNumber(String newPhoneNumber) {
        this.setPhoneNumber(newPhoneNumber);
    }

    public void updateName(String name, String surname) {
        this.setName(name);
        this.setSurname(surname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email=" + email +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    public void updatePersonalInfo(String name,
                                   String surname,
                                   AuthorEmail email,
                                   AuthorPhoneNumber phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
