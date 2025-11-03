package com.TurkcellTakim7.member_service.application.commandHandler;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.member_service.application.commands.UpdateMemberCommand;
import com.TurkcellTakim7.member_service.application.core.CommandHandler;
import com.TurkcellTakim7.member_service.application.dto.UpdatedMemberResponse;
import com.TurkcellTakim7.member_service.application.mapper.UpdateMemberMapper;
import com.TurkcellTakim7.member_service.domain.entities.Member;
import com.TurkcellTakim7.member_service.domain.services.MemberDomainService;
import com.TurkcellTakim7.member_service.domain.valueobjects.Address;
import com.TurkcellTakim7.member_service.domain.valueobjects.Email;
import com.TurkcellTakim7.member_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.member_service.domain.valueobjects.PhoneNumber;

@Component
public class UpdateMemberCommandHandler implements CommandHandler<UpdateMemberCommand, UpdatedMemberResponse> {

  private final MemberDomainService memberDomainService;
  private final UpdateMemberMapper updateMemberMapper;



  public UpdateMemberCommandHandler(MemberDomainService memberDomainService, UpdateMemberMapper updateMemberMapper) {
    this.memberDomainService = memberDomainService;
    this.updateMemberMapper = updateMemberMapper;
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
  
    return updateMemberMapper.toResponse(updatedMember);
  }

}
