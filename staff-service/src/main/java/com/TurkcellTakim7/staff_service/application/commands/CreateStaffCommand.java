package com.TurkcellTakim7.staff_service.application.commands;

import com.TurkcellTakim7.staff_service.application.core.Command;
import com.TurkcellTakim7.staff_service.application.dto.CreatedStaffResponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateStaffCommand(
        @NotBlank @Size(min = 2, max = 50) String name,
        @NotBlank @Size(min = 2, max = 50) String surname,
        @NotBlank String staffPhone
) implements Command<CreatedStaffResponse> {}
