package com.TurkcellTakim7.member_service.domain.events;

import com.TurkcellTakim7.member_service.domain.valueobjects.MemberId;

import java.time.LocalDateTime;

public class MemberDeletedEvent {

    private final MemberId memberId;
    private final LocalDateTime occurredOn;

    public MemberDeletedEvent(MemberId memberId) {
        this.memberId = memberId;
        this.occurredOn = LocalDateTime.now();
    }

    // Getters
    public MemberId getMemberId() {
        return memberId;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String toString() {
        return "MemberDeletedEvent{" +
                "memberId=" + memberId +
                ", occurredOn=" + occurredOn +
                '}';
    }
}

