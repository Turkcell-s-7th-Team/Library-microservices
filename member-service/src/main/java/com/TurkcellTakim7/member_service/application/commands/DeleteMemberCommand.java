package com.TurkcellTakim7.member_service.application.commands;

import java.util.UUID;

import com.TurkcellTakim7.member_service.application.core.Command;

import jakarta.validation.constraints.NotNull;

public record DeleteMemberCommand(
    @NotNull UUID memberId) implements Command<Void> {

}
