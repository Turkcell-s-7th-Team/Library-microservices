package com.TurkcellTakim7.fine_service.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateFineRequest(
        UUID memberId,
        UUID loanId,
        String fineType,     // LATE, LOST, DAMAGE
        BigDecimal amount
) { }

