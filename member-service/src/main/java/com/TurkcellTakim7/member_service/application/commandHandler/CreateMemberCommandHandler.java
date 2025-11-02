package com.TurkcellTakim7.member_service.application.commandHandler;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.member_service.application.commands.CreateMemberCommand;
import com.TurkcellTakim7.member_service.application.core.CommandHandler;
import com.TurkcellTakim7.member_service.application.dto.CreatedMemberRepsonse;
import com.TurkcellTakim7.member_service.application.mapper.CreateMemberMapper;
import com.TurkcellTakim7.member_service.domain.entities.Member;
import com.TurkcellTakim7.member_service.domain.repositories.MemberRepository;

@Component
public class CreateMemberCommandHandler implements CommandHandler<CreateMemberCommand, CreatedMemberRepsonse> {

  private final MemberRepository memberRepository;
  private final CreateMemberMapper createMemberMapper;

  public CreateMemberCommandHandler(MemberRepository memberRepository, CreateMemberMapper createMemberMapper) {
    this.memberRepository = memberRepository;
    this.createMemberMapper = createMemberMapper;
  }

  public CreatedMemberRepsonse handle(CreateMemberCommand command) {
    Member member = createMemberMapper.toDomain(command);
    member = memberRepository.save(member);

    return createMemberMapper.toResponse(member);
  }
}
