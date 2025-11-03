package com.TurkcellTakim7.member_service.application.mapper;

import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.member_service.application.dto.MemberResponse;
import com.TurkcellTakim7.member_service.domain.entities.Member;

@Component
public class GetMemberMapper {

  public MemberResponse toResponse(Member member) {
    Date membershipDate = Date.from(member.getMembershipDate()
        .atStartOfDay(ZoneId.systemDefault()).toInstant());
    return new MemberResponse(
        member.getMemberId().value(),
        member.getName(),
        member.getSurname(),
        member.getEmail().toString(),
        member.getPhoneNumber().toString(),
        member.getAddress().toString(),
        membershipDate,
        member.getMembershipLevel().toString());

  }
}
