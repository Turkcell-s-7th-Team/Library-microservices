package com.TurkcellTakim7.member_service.domain.events;

import com.TurkcellTakim7.member_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.member_service.domain.valueobjects.Email;

import java.time.LocalDateTime;

public class MemberUpdatedEvent {

    private final MemberId memberId;
    private final String name;
    private final String surname;
    private final Email email;
    private final LocalDateTime occurredOn;

    public MemberUpdatedEvent(MemberId memberId, String name, String surname, Email email) {
        this.memberId = memberId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.occurredOn = LocalDateTime.now();
    }

    // Getters
    public MemberId getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Email getEmail() {
        return email;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String toString() {
        return "MemberUpdatedEvent{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email=" + email +
                ", occurredOn=" + occurredOn +
                '}';
    }
}

