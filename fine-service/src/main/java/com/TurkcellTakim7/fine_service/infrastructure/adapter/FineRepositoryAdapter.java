package com.TurkcellTakim7.fine_service.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.fine_service.domain.entities.Fine;
import com.TurkcellTakim7.fine_service.domain.enums.FineStatus;
import com.TurkcellTakim7.fine_service.domain.repositories.FineRepository;
import com.TurkcellTakim7.fine_service.domain.valueobjects.FineId;
import com.TurkcellTakim7.fine_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.fine_service.infrastructure.entities.JpaFineEntity;
import com.TurkcellTakim7.fine_service.infrastructure.mapper.FineEntityMapper;
import com.TurkcellTakim7.fine_service.infrastructure.repository.SpringDataFineRepository;

@Component
public class FineRepositoryAdapter implements FineRepository {

    private final SpringDataFineRepository springDataFineRepository;
    private final FineEntityMapper fineEntityMapper;

    public FineRepositoryAdapter(SpringDataFineRepository springDataFineRepository,
                                 FineEntityMapper fineEntityMapper) {
        this.springDataFineRepository = springDataFineRepository;
        this.fineEntityMapper = fineEntityMapper;
    }

    @Override
    public Fine save(Fine fine) {
        JpaFineEntity entity = fineEntityMapper.toEntity(fine);
        entity = springDataFineRepository.save(entity);
        return fineEntityMapper.toDomain(entity);
    }

    @Override
    public Optional<Fine> findById(FineId fineId) {
        return springDataFineRepository.findById(fineId.value())
                .map(fineEntityMapper::toDomain);
    }

    @Override
    public List<Fine> getAllFines() {
        return springDataFineRepository.findAll().stream()
                .map(fineEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Fine> findByMemberId(MemberId memberId) {
        return springDataFineRepository.findByMemberId(memberId.value()).stream()
                .map(fineEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Fine> findByMemberIdAndStatus(MemberId memberId, FineStatus status) {
        return springDataFineRepository
                .findByMemberIdAndStatus(memberId.value(), status.name())
                .stream()
                .map(fineEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(FineId fineId) {
        springDataFineRepository.deleteById(fineId.value());
    }

    @Override
    public boolean existsByMemberIdAndStatus(MemberId memberId, FineStatus status) {
        return springDataFineRepository.existsByMemberIdAndStatus(memberId.value(), status.name());
    }

    @Override
    public List<Fine> findAllFines() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
