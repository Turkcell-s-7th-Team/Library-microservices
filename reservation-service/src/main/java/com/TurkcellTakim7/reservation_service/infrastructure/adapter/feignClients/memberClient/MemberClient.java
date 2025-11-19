package com.TurkcellTakim7.reservation_service.infrastructure.adapter.feignClients.memberClient;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "member-service", path = "/api/v1/members")
public interface MemberClient {

    @GetMapping("/{memberId}")
    MemberResponse getMemberById(@PathVariable("memberId") UUID memberId);

    @GetMapping("/{memberId}/membership-level")
    String getMembershipLevel(@PathVariable("memberId") UUID memberId);

}
