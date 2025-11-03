package com.TurkcellTakim7.member_service.application.commandHandler;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.member_service.application.commands.DeleteMemberCommand;
import com.TurkcellTakim7.member_service.application.core.CommandHandler;
import com.TurkcellTakim7.member_service.domain.repositories.MemberRepository;
import com.TurkcellTakim7.member_service.domain.services.MemberDomainService;
import com.TurkcellTakim7.member_service.domain.valueobjects.MemberId;

@Component
public class DeleteMemberCommandHandler implements CommandHandler<DeleteMemberCommand, Void> {

  private final MemberRepository memberRepository;
  private final MemberDomainService memberDomainService;

  public DeleteMemberCommandHandler(MemberRepository memberRepository, MemberDomainService memberDomainService) {
    this.memberRepository = memberRepository;
    this.memberDomainService = memberDomainService;
  }

  @Override
  public Void handle(DeleteMemberCommand command) {
    MemberId memberId = new MemberId(command.memberId());
    memberDomainService.deleteById(memberId);
    return null;
  }

}
