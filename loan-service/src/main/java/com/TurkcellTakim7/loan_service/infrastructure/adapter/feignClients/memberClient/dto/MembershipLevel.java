package com.TurkcellTakim7.loan_service.infrastructure.adapter.feignClients.memberClient.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public record MembershipLevel(String value) implements Serializable {

  public static final MembershipLevel STANDARD = new MembershipLevel("STANDARD");
  public static final MembershipLevel GOLD = new MembershipLevel("GOLD");
  public static final MembershipLevel BANNED = new MembershipLevel("BANNED");

  @JsonCreator
  public static MembershipLevel from(String s) {
    return switch (s) {
      case "STANDARD" -> STANDARD;
      case "GOLD" -> GOLD;
      case "BANNED" -> BANNED;
      default -> throw new IllegalArgumentException("Invalid MembershipLevel: " + s);
    };
  }

  @JsonValue
  public String toJson() {
    return value;
  }
}