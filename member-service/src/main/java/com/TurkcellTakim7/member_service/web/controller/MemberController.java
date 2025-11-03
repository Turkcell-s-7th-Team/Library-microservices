package com.TurkcellTakim7.member_service.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkcellTakim7.member_service.application.commands.CreateMemberCommand;
import com.TurkcellTakim7.member_service.application.commands.DeleteMemberCommand;
import com.TurkcellTakim7.member_service.application.commands.UpdateMemberCommand;
import com.TurkcellTakim7.member_service.application.core.CommandHandler;
import com.TurkcellTakim7.member_service.application.core.QueryHandler;
import com.TurkcellTakim7.member_service.application.dto.CreatedMemberRepsonse;
import com.TurkcellTakim7.member_service.application.dto.MemberResponse;
import com.TurkcellTakim7.member_service.application.dto.UpdateMemberRequest;
import com.TurkcellTakim7.member_service.application.dto.UpdatedMemberResponse;
import com.TurkcellTakim7.member_service.application.queries.GetMemberListQuery;
import com.TurkcellTakim7.member_service.application.queries.GetMemberQuery;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/members")

public class MemberController {

  private final CommandHandler<CreateMemberCommand, CreatedMemberRepsonse> createMemberCommandHandler;
  private final CommandHandler<UpdateMemberCommand, UpdatedMemberResponse> updateMemberCommandHandler;
  private final QueryHandler<GetMemberQuery, MemberResponse> getMemberQueryHandler;
  private final QueryHandler<GetMemberListQuery, List<MemberResponse>> getMemberListQueryHandler;
  private final CommandHandler<DeleteMemberCommand, Void> deleteMemberCommandHandler;

  public MemberController(CommandHandler<CreateMemberCommand, CreatedMemberRepsonse> createMemberCommandHandler,
      CommandHandler<UpdateMemberCommand, UpdatedMemberResponse> updateMemberCommandHandler,
      QueryHandler<GetMemberQuery, MemberResponse> getMemberQueryHandler,
      QueryHandler<GetMemberListQuery, List<MemberResponse>> getMemberListQueryHandler,
      CommandHandler<DeleteMemberCommand, Void> deleteMemberCommandHandler) {
    this.createMemberCommandHandler = createMemberCommandHandler;
    this.updateMemberCommandHandler = updateMemberCommandHandler;
    this.getMemberQueryHandler = getMemberQueryHandler;
    this.getMemberListQueryHandler = getMemberListQueryHandler;
    this.deleteMemberCommandHandler = deleteMemberCommandHandler;
  }

  @GetMapping("/{id}")
  public MemberResponse getMember(@PathVariable UUID id) {
    return getMemberQueryHandler.handle(new GetMemberQuery(id));
  }

  @GetMapping
  public List<MemberResponse> getMemberList(GetMemberListQuery query) {
    return getMemberListQueryHandler.handle(query);
  }

  @PostMapping
  public CreatedMemberRepsonse createMember(@RequestBody CreateMemberCommand command) {
    return createMemberCommandHandler.handle(command);
  }

  @PutMapping("/{id}")
  public UpdatedMemberResponse updateMember(
      @PathVariable UUID id,
      @RequestBody @Valid UpdateMemberRequest request) {

    UpdateMemberCommand command = new UpdateMemberCommand(
        id,
        request.name(),
        request.surname(),
        request.email(),
        request.phoneNumber(),
        request.address(),
        request.membershipDate(),
        request.membershipLevel());

    return updateMemberCommandHandler.handle(command);
  }

  @DeleteMapping("/{id}")
  public void deleteMember(@PathVariable UUID id) {
    deleteMemberCommandHandler.handle(new DeleteMemberCommand(id));
  }

}
