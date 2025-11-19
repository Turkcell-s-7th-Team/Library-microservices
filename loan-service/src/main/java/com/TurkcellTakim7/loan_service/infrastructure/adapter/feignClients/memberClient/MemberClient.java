package com.TurkcellTakim7.loan_service.infrastructure.adapter.feignClients.memberClient;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.TurkcellTakim7.loan_service.infrastructure.adapter.feignClients.memberClient.dto.MemberValidationDTO;

@FeignClient(name = "member-service")
public interface MemberClient {

  @GetMapping("/api/v1/members/{memberId}/membership-level")
  MemberValidationDTO getMemberValidationInfo(@PathVariable("memberId") UUID memberId);
}