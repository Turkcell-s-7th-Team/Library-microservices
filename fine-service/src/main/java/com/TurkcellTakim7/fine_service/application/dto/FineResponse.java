package com.TurkcellTakim7.fine_service.application.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public record FineResponse(
        UUID fineId,
        UUID memberId,
        UUID loanId,
        String fineType,
        BigDecimal amount,
        String status,
        Date createdAt,
        Date paidAt
) { }
