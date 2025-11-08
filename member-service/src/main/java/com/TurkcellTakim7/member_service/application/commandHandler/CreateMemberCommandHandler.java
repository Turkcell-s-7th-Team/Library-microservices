package com.TurkcellTakim7.member_service.application.commandHandler;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.member_service.application.commands.CreateMemberCommand;
import com.TurkcellTakim7.member_service.application.core.CommandHandler;
import com.TurkcellTakim7.member_service.application.dto.CreatedMemberResponse;
import com.TurkcellTakim7.member_service.application.mapper.CreateMemberMapper;
import com.TurkcellTakim7.member_service.domain.entities.Member;
import com.TurkcellTakim7.member_service.domain.repositories.MemberRepository;
import com.TurkcellTakim7.member_service.domain.services.MemberDomainService;
import com.TurkcellTakim7.member_service.domain.valueobjects.Email;
import com.TurkcellTakim7.member_service.domain.valueobjects.MembershipLevel;

@Component
public class CreateMemberCommandHandler implements CommandHandler<CreateMemberCommand, CreatedMemberResponse> {

  private final CreateMemberMapper createMemberMapper;
  private final MemberDomainService memberDomainService;
  private final MemberRepository memberRepository;

  public CreateMemberCommandHandler(CreateMemberMapper createMemberMapper, MemberDomainService memberDomainService,
      MemberRepository memberRepository) {
    this.createMemberMapper = createMemberMapper;
    this.memberDomainService = memberDomainService;
    this.memberRepository = memberRepository;
  }

  public CreatedMemberResponse handle(CreateMemberCommand command) {
    Member member = memberDomainService.createMember(
        command.name(),
        command.surname(),
        new Email(command.email()),
        command.phoneNumber(),
        command.address(),
        new MembershipLevel(command.membershipLevel()));
    member = memberRepository.save(member);
    return createMemberMapper.toResponse(member);
  }
}
