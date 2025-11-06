package com.TurkcellTakim7.member_service.application.mapper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.member_service.application.dto.UpdatedMemberResponse;
import com.TurkcellTakim7.member_service.domain.entities.Member;
import com.TurkcellTakim7.member_service.domain.valueobjects.Address;
import com.TurkcellTakim7.member_service.domain.valueobjects.Email;
import com.TurkcellTakim7.member_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.member_service.domain.valueobjects.MembershipLevel;
import com.TurkcellTakim7.member_service.domain.valueobjects.PhoneNumber;

@Component
public class UpdateMemberMapper {

  public Email toEmail(String email) {
    return new Email(email);
  }

  public UpdatedMemberResponse toResponse(Member member) {
    return new UpdatedMemberResponse(
        toUuid(member),
        member.getName(),
        member.getSurname(),
        member.getEmail().toString(),
        member.getPhoneNumber().toString(),
        member.getAddress().toString(),
        toDate(member.getMembershipDate()),
        member.getMembershipLevel().toString());
  }

  private UUID toUuid(Member member) {
    return member.getMemberId() != null ? member.getMemberId().value() : null;
  }

  private Date toDate(LocalDate localDate) {
    if (localDate == null) {
      return null;
    }
    return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  public Member toDomain(UpdatedMemberResponse response) {
    LocalDate membershipDate = response.membershipDate()
        .toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();

    return Member.rehydrate(
        new MemberId(response.id()),
        response.name(),
        response.surname(),
        toEmail(response.email()),
        new PhoneNumber(response.phoneNumber()),
        new Address(response.address()),
        membershipDate,
        new MembershipLevel(response.membershipLevel()));
  }
}
