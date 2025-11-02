package com.TurkcellTakim7.author_service.infrastructure.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "authors")
public class AuthorEntity {

    @Id
    @Column(name = "id", columnDefinition = "UUID", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "surname", nullable = false, length = 100)
    private String surname;

    @Column(name = "email", unique = true, length = 255, nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true, length = 50, nullable = false)
    private String phoneNumber;

    public AuthorEntity() {
    }

    public AuthorEntity(UUID id, String name, String surname, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
