package com.TurkcellTakim7.loan_service.infrastructure.adapter.feignClients.memberClient.dto;

public record MemberValidationDTO(
    MembershipLevel membershipLevel) {
}

enum MembershipLevel {
  STANDARD, GOLD, BANNED
}