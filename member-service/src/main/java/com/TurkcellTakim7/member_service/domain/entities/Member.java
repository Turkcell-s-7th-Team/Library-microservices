package com.TurkcellTakim7.member_service.domain.entities;

import com.TurkcellTakim7.member_service.domain.valueobjects.*;

import java.time.LocalDate;

public class Member {

    private final MemberId memberId;
    private String name;
    private String surname;
    private Email email;
    private PhoneNumber phoneNumber;
    private Address address;
    private LocalDate membershipDate;
    private MembershipLevel membershipLevel;

    public Member(MemberId memberId, String name, String surname, Email email, PhoneNumber phoneNumber, Address address, LocalDate membershipDate, MembershipLevel membershipLevel) {
        this.memberId = memberId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.membershipDate = membershipDate;
        this.membershipLevel = membershipLevel;
    }


}

