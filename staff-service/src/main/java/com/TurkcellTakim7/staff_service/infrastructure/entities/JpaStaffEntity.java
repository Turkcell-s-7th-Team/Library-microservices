package com.TurkcellTakim7.staff_service.infrastructure.entities;

import java.util.UUID;

@Entity
@Table(name = "staff")
public class JpaStaffEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    DbColumn(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "staff_phone", nullable = false, unique = true, length = 20)
    private String staffPhone;

    public JpaStaffEntity() {
    }

    public JpaStaffEntity(UUID id, String name, String surname, String staffPhone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.staffPhone = staffPhone;
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

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }
}
