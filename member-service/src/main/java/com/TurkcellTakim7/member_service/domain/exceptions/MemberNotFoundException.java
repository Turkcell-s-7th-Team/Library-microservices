package com.TurkcellTakim7.member_service.domain.exceptions;

import com.TurkcellTakim7.member_service.domain.valueobjects.MemberId;

public class MemberNotFoundException extends RuntimeException {

    private final MemberId memberId;

    public MemberNotFoundException(MemberId memberId) {
        super("Member not found with id: " + memberId.value());
        this.memberId = memberId;
    }

    public MemberNotFoundException(String message, MemberId memberId) {
        super(message);
        this.memberId = memberId;
    }

    public MemberId getMemberId() {
        return memberId;
    }
}

