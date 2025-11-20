package com.TurkcellTakim7.staff_service.domain.entities;

import java.util.Objects;

import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffId;
import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffPhone;

public class Staff {

    private final StaffId staffId;
    private String name;
    private String surname;
    private StaffPhone staffPhone;

    private Staff(StaffId staffId, String name, String surname, StaffPhone staffPhone) {
        this.staffId = Objects.requireNonNull(staffId, "StaffId cannot be null");
        this.name = validateName(name);
        this.surname = validateName(surname);
        this.staffPhone = Objects.requireNonNull(staffPhone, "StaffPhone cannot be null");
    }

    // Factory Methods
    public static Staff create(String name, String surname, StaffPhone staffPhone) {
        validateName(name);
        validateName(surname);
        return new Staff(StaffId.generate(), name, surname, staffPhone);
    }

    public static Staff rehydrate(StaffId staffId, String name, String surname, StaffPhone staffPhone) {
        return new Staff(staffId, name, surname, staffPhone);
    }

    // Getters
    public StaffId getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public StaffPhone getStaffPhone() {
        return staffPhone;
    }

    public String getFullName() {
        return name + " " + surname;
    }

    // Business Methods
    public void updatePersonalInfo(String name, String surname, StaffPhone phone) {
        this.name = validateName(name);
        this.surname = validateName(surname);
        this.staffPhone = Objects.requireNonNull(phone, "StaffPhone cannot be null");
    }

    // Validation Methods
    private static String validateName(String name) {
        Objects.requireNonNull(name, "Name cannot be null");
        if (name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        if (name.length() < 2)
            throw new IllegalArgumentException("Name must be at least 2 characters long");
        if (name.length() > 50)
            throw new IllegalArgumentException("Name cannot exceed 50 characters");
        return name.trim();
    }

    // Equality
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Staff))
            return false;
        Staff staff = (Staff) o;
        return Objects.equals(staffId, staff.staffId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffId);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", staffPhone='" + staffPhone + '\'' +
                '}';
    }
}
