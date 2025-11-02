package com.TurkcellTakim7.member_service.domain.exceptions;

import com.TurkcellTakim7.member_service.domain.valueobjects.MemberId;

public class InvalidMemberOperationException extends RuntimeException {

    private final MemberId memberId;

    public InvalidMemberOperationException(String message, MemberId memberId) {
        super(message);
        this.memberId = memberId;
    }

    public InvalidMemberOperationException(String message, MemberId memberId, Throwable cause) {
        super(message, cause);
        this.memberId = memberId;
    }

    public MemberId getMemberId() {
        return memberId;
    }
}

