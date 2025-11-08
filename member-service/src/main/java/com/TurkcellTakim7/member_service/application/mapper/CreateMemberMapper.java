package com.TurkcellTakim7.member_service.application.mapper;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.member_service.application.commands.CreateMemberCommand;
import com.TurkcellTakim7.member_service.application.dto.CreatedMemberResponse;
import com.TurkcellTakim7.member_service.domain.entities.Member;
import com.TurkcellTakim7.member_service.domain.valueobjects.Address;
import com.TurkcellTakim7.member_service.domain.valueobjects.MembershipLevel;
import com.TurkcellTakim7.member_service.domain.valueobjects.PhoneNumber;

@Component
public class CreateMemberMapper {

  public Member toDomain(CreateMemberCommand command) {

    return Member.create(command.name(), command.surname(),
        new com.TurkcellTakim7.member_service.domain.valueobjects.Email(command.email()),
        new PhoneNumber(command.phoneNumber()),
        new Address(command.address()),
        command.membershipDate(),
        new MembershipLevel(command.membershipLevel()));
  }

  public CreatedMemberResponse toResponse(Member member) {
    return new CreatedMemberResponse(
        member.getName(),
        member.getSurname(),
        member.getEmail().toString(),
        member.getPhoneNumber().toString(),
        member.getAddress().toString(),
        member.getMembershipDate(),
        member.getMembershipLevel().toString());
  }
}
