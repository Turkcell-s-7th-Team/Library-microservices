package com.TurkcellTakim7.fine_service.application.mappers;

import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.fine_service.application.dto.FineResponse;
import com.TurkcellTakim7.fine_service.domain.entities.Fine;

@Component
public class GetFineMapper {

    public FineResponse toResponse(Fine fine) {

        Date createdAt = Date.from(
                fine.getCreatedAt().atStartOfDay(ZoneId.systemDefault()).toInstant()
        );

        Date paidAt = fine.getPaidAt() != null
                ? Date.from(fine.getPaidAt().atStartOfDay(ZoneId.systemDefault()).toInstant())
                : null;

        return new FineResponse(
                fine.getFineId().value(),
                fine.getMemberId().value(),
                fine.getLoanId().value(),
                fine.getFineType().toString(),
                fine.getAmount().value(),
                fine.getStatus().toString(),
                createdAt,
                paidAt
        );
    }
}
