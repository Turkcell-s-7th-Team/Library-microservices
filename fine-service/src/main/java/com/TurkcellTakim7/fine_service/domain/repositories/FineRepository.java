package com.TurkcellTakim7.fine_service.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.TurkcellTakim7.fine_service.domain.entities.Fine;
import com.TurkcellTakim7.fine_service.domain.enums.FineStatus;
import com.TurkcellTakim7.fine_service.domain.valueobjects.FineId;
import com.TurkcellTakim7.fine_service.domain.valueobjects.MemberId;

public interface FineRepository {

    Fine save(Fine fine);

    Optional<Fine> findById(FineId fineId);

    List<Fine> getAllFines();

    List<Fine> findByMemberId(MemberId memberId);

    List<Fine> findByMemberIdAndStatus(MemberId memberId, FineStatus status);

    void deleteById(FineId fineId);

    boolean existsByMemberIdAndStatus(MemberId memberId, FineStatus status);
   
    List<Fine> findAllFines();
}

