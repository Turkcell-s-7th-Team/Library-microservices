package com.TurkcellTakim7.fine_service.application.mappers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.fine_service.application.dto.UpdatedFineResponse;
import com.TurkcellTakim7.fine_service.domain.entities.Fine;
import com.TurkcellTakim7.fine_service.domain.enums.FineStatus;
import com.TurkcellTakim7.fine_service.domain.enums.FineType;
import com.TurkcellTakim7.fine_service.domain.valueobjects.Amount;
import com.TurkcellTakim7.fine_service.domain.valueobjects.FineId;
import com.TurkcellTakim7.fine_service.domain.valueobjects.LoanId;
import com.TurkcellTakim7.fine_service.domain.valueobjects.MemberId;

@Component
public class UpdateFineMapper {

    // ---- DOMAIN → RESPONSE ----
    public UpdatedFineResponse toResponse(Fine fine) {
        return new UpdatedFineResponse(
                toUuid(fine),
                fine.getMemberId().value(),
                fine.getLoanId().value(),
                fine.getFineType().name(),
                fine.getAmount().value(),
                toDate(fine.getCreatedAt()),
                toDate(fine.getPaidAt()),
                fine.getStatus().name()
        );
    }

    private UUID toUuid(Fine fine) {
        return fine.getFineId() != null ? fine.getFineId().value() : null;
    }

    private Date toDate(LocalDate date) {
        if (date == null) return null;
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    // ---- RESPONSE → DOMAIN (rehydrate) ----
    public Fine toDomain(UpdatedFineResponse response) {
        LocalDate createdAt = response.createdAt()
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate paidAt = response.paidAt() != null
                ? response.paidAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                : null;

        return Fine.rehydrate(
                new FineId(response.fineId()),
                new MemberId(response.memberId()),
                new LoanId(response.loanId()),
                FineType.valueOf(response.fineType()),
                new Amount(response.amount()),
                createdAt,
                FineStatus.valueOf(response.status()),
                paidAt
        );
    }
}

