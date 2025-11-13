package com.TurkcellTakim7.member_service.application.commands;

import java.time.LocalDate;
import java.util.UUID;

import com.TurkcellTakim7.member_service.application.core.Command;
import com.TurkcellTakim7.member_service.application.dto.UpdatedMemberResponse;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateMemberCommand(
    @NotNull UUID memberId,
    @NotBlank @Size(min = 2, max = 50) String name,
    @NotBlank @Size(min = 2, max = 50) String surname,
    @NotBlank @Email String email,
    @NotBlank String phoneNumber,
    @NotBlank String address,
    @NotNull LocalDate membershipDate,
    @NotBlank String membershipLevel) implements Command<UpdatedMemberResponse> {

}
