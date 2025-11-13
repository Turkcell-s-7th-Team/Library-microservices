package com.TurkcellTakim7.staff_service.domain.exceptions;

import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffId;

public class InvalidStaffOperationException extends RuntimeException {

    private final StaffId staffId;

    public InvalidStaffOperationException(String message, StaffId staffId) {
        super(message);
        this.staffId = staffId;
    }

    public InvalidStaffOperationException(String message, StaffId staffId, Throwable cause) {
        super(message, cause);
        this.staffId = staffId;
    }

    public StaffId getStaffId() {
        return staffId;
    }
}
