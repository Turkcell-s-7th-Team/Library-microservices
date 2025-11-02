package com.TurkcellTakim7.member_service.domain.entities;

import com.TurkcellTakim7.member_service.domain.valueobjects.*;

import java.time.LocalDate;
import java.util.Objects;


public class Member {

    private final MemberId memberId;
    private String name;
    private String surname;
    private Email email;
    private PhoneNumber phoneNumber;
    private Address address;
    private LocalDate membershipDate;
    private MembershipLevel membershipLevel;

    public Member(MemberId memberId, String name, String surname, Email email, PhoneNumber phoneNumber, Address address,
            LocalDate membershipDate, MembershipLevel membershipLevel) {
        this.memberId = Objects.requireNonNull(memberId, "MemberId cannot be null");
        this.name = validateName(name);
        this.surname = validateName(surname);
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.phoneNumber = Objects.requireNonNull(phoneNumber, "PhoneNumber cannot be null");
        this.address = Objects.requireNonNull(address, "Address cannot be null");
        this.membershipDate = Objects.requireNonNull(membershipDate, "MembershipDate cannot be null");
        this.membershipLevel = Objects.requireNonNull(membershipLevel, "MembershipLevel cannot be null");
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

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    // Business Methods
    public void updatePersonalInfo(String name, String surname, Email email, PhoneNumber phoneNumber, Address address) {
        this.name = validateName(name);
        this.surname = validateName(surname);
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.phoneNumber = Objects.requireNonNull(phoneNumber, "PhoneNumber cannot be null");
        this.address = Objects.requireNonNull(address, "Address cannot be null");
    }

    public void updateMembershipLevel(MembershipLevel newLevel) {
        this.membershipLevel = Objects.requireNonNull(newLevel, "MembershipLevel cannot be null");
    }

    public void ban() {
        this.membershipLevel = MembershipLevel.BANNED;
    }

    public void unban() {
        this.membershipLevel = MembershipLevel.STANDARD;
    }

    public boolean isBanned() {
        return this.membershipLevel.equals(MembershipLevel.BANNED);
    }

    public boolean isGoldMember() {
        return this.membershipLevel.equals(MembershipLevel.GOLD);
    }

    public String getFullName() {
        return name + " " + surname;
    }

    // Validation Methods
    private String validateName(String name) {
        Objects.requireNonNull(name, "Name cannot be null");
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (name.length() < 2) {
            throw new IllegalArgumentException("Name must be at least 2 characters long");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("Name cannot exceed 50 characters");
        }
        return name.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Member member = (Member) o;
        return Objects.equals(memberId, member.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email=" + email +
                ", membershipLevel=" + membershipLevel +
                '}';
    }
}
