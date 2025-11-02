package com.TurkcellTakim7.member_service.infrastructure.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "members")
public class JpaMemberEntity {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private UUID id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "surname", nullable = false, length = 50)
  private String surname;

  @Column(name = "email", nullable = false, unique = true, length = 100)
  private String email;

  @Column(name = "phone_number", nullable = false, length = 20)
  private String phoneNumber;

  @Column(name = "address", nullable = false, length = 255)
  private String address;

  @Column(name = "membership_date", nullable = false)
  private LocalDate membershipDate;

  @Column(name = "membership_level", nullable = false, length = 20)
  private String membershipLevel;

  public JpaMemberEntity() {
  }

  public JpaMemberEntity(UUID id, String name, String surname, String email,
      String phoneNumber, String address, LocalDate membershipDate,
      String membershipLevel) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.membershipDate = membershipDate;
    this.membershipLevel = membershipLevel;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public LocalDate getMembershipDate() {
    return membershipDate;
  }

  public void setMembershipDate(LocalDate membershipDate) {
    this.membershipDate = membershipDate;
  }

  public String getMembershipLevel() {
    return membershipLevel;
  }

  public void setMembershipLevel(String membershipLevel) {
    this.membershipLevel = membershipLevel;
  }
}
