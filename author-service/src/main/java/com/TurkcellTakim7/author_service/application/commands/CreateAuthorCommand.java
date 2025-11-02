package com.TurkcellTakim7.author_service.application.commands;

import com.TurkcellTakim7.author_service.application.core.Command.Command;
import com.TurkcellTakim7.author_service.application.dtos.CreatedAuthorResponse;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAuthorCommand(
        @NotBlank @Size(min = 2, max = 50) String name,
        @NotBlank @Size(min = 2, max = 50) String surname,
        @NotBlank @Email String email,
        @NotBlank String phoneNumber) implements Command<CreatedAuthorResponse> {

}
