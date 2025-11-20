package com.TurkcellTakim7.fine_service.application.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public record CreatedFineResponse(
        UUID fineId,
        UUID memberId,
        UUID loanId,
        String fineType,
        BigDecimal amount,
        Date createdAt,
        String status
) { }
