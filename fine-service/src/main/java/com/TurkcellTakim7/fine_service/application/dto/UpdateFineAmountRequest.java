package com.TurkcellTakim7.fine_service.application.dto;

import java.math.BigDecimal;

public record UpdateFineAmountRequest(
        BigDecimal amount
) { }

