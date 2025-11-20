package com.TurkcellTakim7.fine_service.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.fine_service.domain.entities.Fine;
import com.TurkcellTakim7.fine_service.domain.enums.FineStatus;
import com.TurkcellTakim7.fine_service.domain.enums.FineType;
import com.TurkcellTakim7.fine_service.domain.valueobjects.Amount;
import com.TurkcellTakim7.fine_service.domain.valueobjects.FineId;
import com.TurkcellTakim7.fine_service.domain.valueobjects.LoanId;
import com.TurkcellTakim7.fine_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.fine_service.infrastructure.entities.JpaFineEntity;

@Component
public class FineEntityMapper {

    // DOMAIN → JPA ENTITY
    public JpaFineEntity toEntity(Fine fine) {
        JpaFineEntity entity = new JpaFineEntity();
        entity.setId(fine.getFineId().value());
        entity.setMemberId(fine.getMemberId().value());
        entity.setLoanId(fine.getLoanId().value());
        entity.setFineType(fine.getFineType().name());
        entity.setAmount(fine.getAmount().value());
        entity.setCreatedAt(fine.getCreatedAt());
        entity.setStatus(fine.getStatus().name());
        entity.setPaidAt(fine.getPaidAt());
        return entity;
    }

    // JPA ENTITY → DOMAIN
    public Fine toDomain(JpaFineEntity entity) {
        return Fine.rehydrate(
                new FineId(entity.getId()),
                new MemberId(entity.getMemberId()),
                new LoanId(entity.getLoanId()),
                FineType.valueOf(entity.getFineType()),
                new Amount(entity.getAmount()),
                entity.getCreatedAt(),
                FineStatus.valueOf(entity.getStatus()),
                entity.getPaidAt()
        );
    }
}
