package com.TurkcellTakim7.member_service.application.commandHandler;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.member_service.application.commands.UpdateMemberCommand;
import com.TurkcellTakim7.member_service.application.core.CommandHandler;
import com.TurkcellTakim7.member_service.application.dto.UpdatedMemberResponse;
import com.TurkcellTakim7.member_service.application.mapper.UpdateMemberMapper;
import com.TurkcellTakim7.member_service.domain.entities.Member;
import com.TurkcellTakim7.member_service.domain.repositories.MemberRepository;
import com.TurkcellTakim7.member_service.domain.services.MemberDomainService;
import com.TurkcellTakim7.member_service.domain.valueobjects.Address;
import com.TurkcellTakim7.member_service.domain.valueobjects.Email;
import com.TurkcellTakim7.member_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.member_service.domain.valueobjects.PhoneNumber;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class UpdateMemberCommandHandler implements CommandHandler<UpdateMemberCommand, UpdatedMemberResponse> {

  private final MemberDomainService memberDomainService;
  private final UpdateMemberMapper updateMemberMapper;
  private final MemberRepository memberRepository;

  public UpdateMemberCommandHandler(MemberDomainService memberDomainService, UpdateMemberMapper updateMemberMapper,
      MemberRepository memberRepository) {
    this.memberDomainService = memberDomainService;
    this.updateMemberMapper = updateMemberMapper;
    this.memberRepository = memberRepository;
  }

  @Override
  public UpdatedMemberResponse handle(UpdateMemberCommand command) {
    MemberId memberId = new MemberId(command.memberId());

    Member updatedMember = memberDomainService.updateMember(
        memberId,
        command.name(),
        command.surname(),
        new Email(command.email()),
        new PhoneNumber(command.phoneNumber()),
        new Address(command.address()));
    updatedMember = memberRepository.save(updatedMember);
    return updateMemberMapper.toResponse(updatedMember);
  }

}
