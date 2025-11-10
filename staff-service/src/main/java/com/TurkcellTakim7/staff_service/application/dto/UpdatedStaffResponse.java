package com.TurkcellTakim7.staff_service.application.dto;

import java.util.UUID;

public class UpdatedStaffResponse {

    private final UUID id;
    private final String name;
    private final String surname;
    private final String staffPhone;

    public UpdatedStaffResponse(UUID id, String name, String surname, String staffPhone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.staffPhone = staffPhone;
    }

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String surname() {
        return surname;
    }

    public String staffPhone() {
        return staffPhone;
    }
}
