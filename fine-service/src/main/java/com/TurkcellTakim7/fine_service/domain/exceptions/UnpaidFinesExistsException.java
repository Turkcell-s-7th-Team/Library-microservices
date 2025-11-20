package com.TurkcellTakim7.fine_service.domain.exceptions;

import com.TurkcellTakim7.fine_service.domain.valueobjects.MemberId;

public class UnpaidFinesExistsException extends RuntimeException {

    private final MemberId memberId;

    public UnpaidFinesExistsException(MemberId memberId) {
        super("Member has unpaid fines: " + memberId.value());
        this.memberId = memberId;
    }

    public UnpaidFinesExistsException(String message, MemberId memberId) {
        super(message);
        this.memberId = memberId;
    }

    public MemberId getMemberId() {
        return memberId;
    }
}

