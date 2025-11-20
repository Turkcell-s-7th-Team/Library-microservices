package com.TurkcellTakim7.fine_service.application.commands;

import java.math.BigDecimal;
import java.util.UUID;

import com.TurkcellTakim7.fine_service.application.core.Command;
import com.TurkcellTakim7.fine_service.application.dto.CreatedFineResponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateFineCommand(

        @NotNull UUID memberId,

        @NotNull UUID loanId,

        @NotBlank String fineType,   // "LATE_RETURN", "LOST", "DAMAGED"

        @NotNull @Positive BigDecimal amount

) implements Command<CreatedFineResponse> {}
