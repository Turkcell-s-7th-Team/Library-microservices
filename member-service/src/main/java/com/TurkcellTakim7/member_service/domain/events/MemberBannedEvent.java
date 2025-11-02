package com.TurkcellTakim7.member_service.domain.events;

import com.TurkcellTakim7.member_service.domain.valueobjects.MemberId;

import java.time.LocalDateTime;

public class MemberBannedEvent {

    private final MemberId memberId;
    private final LocalDateTime occurredOn;

    public MemberBannedEvent(MemberId memberId) {
        this.memberId = memberId;
        this.occurredOn = LocalDateTime.now();
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String toString() {
        return "MemberBannedEvent{" +
                "memberId=" + memberId +
                ", occurredOn=" + occurredOn +
                '}';
    }
}

