package com.TurkcellTakim7.member_service.application.commandHandler;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.member_service.application.commands.CreateMemberCommand;
import com.TurkcellTakim7.member_service.application.core.CommandHandler;
import com.TurkcellTakim7.member_service.application.dto.CreatedMemberRepsonse;
import com.TurkcellTakim7.member_service.application.mapper.CreateMemberMapper;
import com.TurkcellTakim7.member_service.domain.entities.Member;
import com.TurkcellTakim7.member_service.domain.services.MemberDomainService;
import com.TurkcellTakim7.member_service.domain.valueobjects.Email;
import com.TurkcellTakim7.member_service.domain.valueobjects.MembershipLevel;

@Component
public class CreateMemberCommandHandler implements CommandHandler<CreateMemberCommand, CreatedMemberRepsonse> {

  private final CreateMemberMapper createMemberMapper;
  private final MemberDomainService memberDomainService;

  public CreateMemberCommandHandler(CreateMemberMapper createMemberMapper, MemberDomainService memberDomainService) {
    this.createMemberMapper = createMemberMapper;
    this.memberDomainService = memberDomainService;
  }

  public CreatedMemberRepsonse handle(CreateMemberCommand command) {
    Member member = memberDomainService.createMember(
        command.name(),
        command.surname(),
        new Email(command.email()),
        command.phoneNumber(),
        command.address(),
        new MembershipLevel(command.membershipLevel()));

    return createMemberMapper.toResponse(member);
  }
}
