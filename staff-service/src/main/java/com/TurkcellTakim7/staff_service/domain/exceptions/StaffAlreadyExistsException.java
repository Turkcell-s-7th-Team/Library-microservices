package com.TurkcellTakim7.staff_service.domain.exceptions;

public class StaffAlreadyExistsException extends RuntimeException {

    private final String staffPhone;

    public StaffAlreadyExistsException(String staffPhone) {
        super("Staff already exists with phone number: " + staffPhone);
        this.staffPhone = staffPhone;
    }

    public StaffAlreadyExistsException(String message, String staffPhone) {
        super(message);
        this.staffPhone = staffPhone;
    }

    public String getStaffPhone() {
        return staffPhone;
    }
}
