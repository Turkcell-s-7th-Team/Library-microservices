package com.TurkcellTakim7.fine_service.application.commands;

import java.math.BigDecimal;
import java.util.UUID;

import com.TurkcellTakim7.fine_service.application.core.Command;
import com.TurkcellTakim7.fine_service.application.dto.UpdatedFineResponse;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateFineAmountCommand(

        @NotNull UUID fineId,

        @NotNull @Positive BigDecimal amount

) implements Command<UpdatedFineResponse> {}

