package com.TurkcellTakim7.member_service.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkcellTakim7.member_service.application.commands.CreateMemberCommand;
import com.TurkcellTakim7.member_service.application.core.CommandHandler;
import com.TurkcellTakim7.member_service.application.dto.CreatedMemberRepsonse;

@RestController
@RequestMapping("/api/v1/members")

public class MemberController {

  private final CommandHandler<CreateMemberCommand, CreatedMemberRepsonse> createMemberCommandHandler;

  public MemberController(CommandHandler<CreateMemberCommand, CreatedMemberRepsonse> createMemberCommandHandler) {
    this.createMemberCommandHandler = createMemberCommandHandler;
  }

  @PostMapping
  public CreatedMemberRepsonse createMember(@RequestBody CreateMemberCommand command) {
    return createMemberCommandHandler.handle(command);
  }

}
