package com.TurkcellTakim7.loan_service.infrastructure.adapter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.loan_service.domain.entities.Loan;
import com.TurkcellTakim7.loan_service.domain.enums.LoanStatus;
import com.TurkcellTakim7.loan_service.domain.repositories.LoanRepository;
import com.TurkcellTakim7.loan_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.loan_service.domain.valueobjects.LoanId;
import com.TurkcellTakim7.loan_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.loan_service.infrastructure.entities.LoanEntity;
import com.TurkcellTakim7.loan_service.infrastructure.mapper.LoanEntityMapper;
import com.TurkcellTakim7.loan_service.infrastructure.repository.JpaLoanRepository;

@Component
public class LoanRepositoryAdapter implements LoanRepository {

    private final JpaLoanRepository jpaLoanRepository;
    private final LoanEntityMapper loanEntityMapper;

    public LoanRepositoryAdapter(JpaLoanRepository jpaLoanRepository,
            LoanEntityMapper loanEntityMapper) {
        this.jpaLoanRepository = jpaLoanRepository;
        this.loanEntityMapper = loanEntityMapper;
    }

    @Override
    public Loan save(Loan loan) {
        LoanEntity entity = loanEntityMapper.toEntity(loan);
        entity = jpaLoanRepository.save(entity);
        return loanEntityMapper.toDomain(entity);
    }

    @Override
    public Optional<Loan> findById(LoanId id) {
        return jpaLoanRepository.findById(id.value())
                .map(loanEntityMapper::toDomain);
    }

    @Override
    public List<Loan> findByMemberId(MemberId memberId) {
        return jpaLoanRepository.findByMemberId(memberId.value())
                .stream()
                .map(loanEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Loan> findActiveByMemberId(MemberId memberId) {
        return jpaLoanRepository
                .findByMemberIdAndStatus(memberId.value(), LoanStatus.ACTIVE.name())
                .stream()
                .map(loanEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Loan> findOverdueLoans(LocalDate asOfDate) {
        return jpaLoanRepository
                .findByDueDateBeforeAndStatus(asOfDate, LoanStatus.ACTIVE.name())
                .stream()
                .map(loanEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Loan> findActiveByBookId(BookId bookId) {
        return jpaLoanRepository
                .findByBookId(bookId.value())
                .stream()
                .filter(e -> LoanStatus.ACTIVE.name().equals(e.getStatus()))
                .map(loanEntityMapper::toDomain)
                .toList();
    }
}
