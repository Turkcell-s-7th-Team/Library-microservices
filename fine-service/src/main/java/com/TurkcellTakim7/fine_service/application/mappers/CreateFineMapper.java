package com.TurkcellTakim7.fine_service.application.mappers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.fine_service.application.commands.CreateFineCommand;
import com.TurkcellTakim7.fine_service.application.dto.CreatedFineResponse;
import com.TurkcellTakim7.fine_service.domain.entities.Fine;
import com.TurkcellTakim7.fine_service.domain.enums.FineType;
import com.TurkcellTakim7.fine_service.domain.valueobjects.Amount;
import com.TurkcellTakim7.fine_service.domain.valueobjects.LoanId;
import com.TurkcellTakim7.fine_service.domain.valueobjects.MemberId;

@Component
public class CreateFineMapper {

    public Fine toDomain(CreateFineCommand command) {
        return Fine.create(
                new MemberId(command.memberId()),
                new LoanId(command.loanId()),
                FineType.valueOf(command.fineType()),
                new Amount(command.amount()),
                LocalDate.now()
        );
    }

    public CreatedFineResponse toResponse(Fine fine) {

        Date createdAt = Date.from(
                fine.getCreatedAt()
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()
        );

        return new CreatedFineResponse(
                fine.getFineId().value(),
                fine.getMemberId().value(),
                fine.getLoanId().value(),
                fine.getFineType().name(),
                fine.getAmount().value(),
                createdAt,
                fine.getStatus().name()
        );
    }
}
