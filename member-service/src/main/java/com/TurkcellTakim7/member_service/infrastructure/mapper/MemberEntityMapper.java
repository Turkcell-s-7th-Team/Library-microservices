package com.TurkcellTakim7.member_service.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.member_service.domain.entities.Member;
import com.TurkcellTakim7.member_service.domain.valueobjects.Address;
import com.TurkcellTakim7.member_service.domain.valueobjects.Email;
import com.TurkcellTakim7.member_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.member_service.domain.valueobjects.MembershipLevel;
import com.TurkcellTakim7.member_service.domain.valueobjects.PhoneNumber;
import com.TurkcellTakim7.member_service.infrastructure.entities.JpaMemberEntity;

@Component
public class MemberEntityMapper {

  public JpaMemberEntity toEntity(Member member) {
    JpaMemberEntity memberEntity = new JpaMemberEntity();
    memberEntity.setId(member.getMemberId().value());
    memberEntity.setName(member.getName());
    memberEntity.setSurname(member.getSurname());
    memberEntity.setEmail(member.getEmail().toString());
    memberEntity.setPhoneNumber(member.getPhoneNumber().toString());
    memberEntity.setAddress(member.getAddress().toString());
    memberEntity.setMembershipDate(member.getMembershipDate());
    memberEntity.setMembershipLevel(member.getMembershipLevel().toString());
    return memberEntity;
  }

  public Member toDomain(JpaMemberEntity entity) {
    return Member.rehydrate(
        new MemberId(entity.getId()),
        entity.getName(),
        entity.getSurname(),
        new Email(entity.getEmail()),
        new PhoneNumber(entity.getPhoneNumber()),
        new Address(entity.getAddress()),
        entity.getMembershipDate(),
        new MembershipLevel(entity.getMembershipLevel()));
  }
}
