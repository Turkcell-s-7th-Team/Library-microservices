package com.TurkcellTakim7.member_service.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkcellTakim7.member_service.application.commands.CreateMemberCommand;
import com.TurkcellTakim7.member_service.application.core.CommandHandler;
import com.TurkcellTakim7.member_service.application.core.QueryHandler;
import com.TurkcellTakim7.member_service.application.dto.CreatedMemberRepsonse;
import com.TurkcellTakim7.member_service.application.dto.MemberResponse;
import com.TurkcellTakim7.member_service.application.queries.GetMemberListQuery;
import com.TurkcellTakim7.member_service.application.queries.GetMemberQuery;

@RestController
@RequestMapping("/api/v1/members")

public class MemberController {

  private final CommandHandler<CreateMemberCommand, CreatedMemberRepsonse> createMemberCommandHandler;
  private final QueryHandler<GetMemberQuery, MemberResponse> getMemberQueryHandler;
  private final QueryHandler<GetMemberListQuery, List<MemberResponse>> getMemberListQueryHandler;

  public MemberController(CommandHandler<CreateMemberCommand, CreatedMemberRepsonse> createMemberCommandHandler,
      QueryHandler<GetMemberQuery, MemberResponse> getMemberQueryHandler,
      QueryHandler<GetMemberListQuery, List<MemberResponse>> getMemberListQueryHandler) {
    this.createMemberCommandHandler = createMemberCommandHandler;
    this.getMemberQueryHandler = getMemberQueryHandler;
    this.getMemberListQueryHandler = getMemberListQueryHandler;
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

}
