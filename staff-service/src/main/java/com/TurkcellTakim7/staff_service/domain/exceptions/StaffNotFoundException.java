package com.TurkcellTakim7.staff_service.domain.exceptions;

import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffId;

public class StaffNotFoundException extends RuntimeException {

    private final StaffId staffId;

    public StaffNotFoundException(StaffId staffId) {
        super("Staff not found with id: " + staffId.value());
        this.staffId = staffId;
    }

    public StaffNotFoundException(String message, StaffId staffId) {
        super(message);
        this.staffId = staffId;
    }

    public StaffId getStaffId() {
        return staffId;
    }
}
