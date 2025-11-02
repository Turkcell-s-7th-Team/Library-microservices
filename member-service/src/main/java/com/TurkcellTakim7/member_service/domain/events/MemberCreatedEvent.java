package com.TurkcellTakim7.member_service.domain.events;

import com.TurkcellTakim7.member_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.member_service.domain.valueobjects.Email;
import com.TurkcellTakim7.member_service.domain.valueobjects.MembershipLevel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MemberCreatedEvent {

    private final MemberId memberId;
    private final String name;
    private final String surname;
    private final Email email;
    private final MembershipLevel membershipLevel;
    private final LocalDate membershipDate;
    private final LocalDateTime occurredOn;

    public MemberCreatedEvent(MemberId memberId, String name, String surname,
            Email email, MembershipLevel membershipLevel,
            LocalDate membershipDate) {
        this.memberId = memberId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.membershipLevel = membershipLevel;
        this.membershipDate = membershipDate;
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

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String toString() {
        return "MemberCreatedEvent{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email=" + email +
                ", membershipLevel=" + membershipLevel +
                ", membershipDate=" + membershipDate +
                ", occurredOn=" + occurredOn +
                '}';
    }
}

