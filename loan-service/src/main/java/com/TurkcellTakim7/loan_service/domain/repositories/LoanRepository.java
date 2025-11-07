package com.TurkcellTakim7.loan_service.domain.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.TurkcellTakim7.loan_service.domain.entities.Loan;
import com.TurkcellTakim7.loan_service.domain.valueobjects.LoanId;
import com.TurkcellTakim7.loan_service.domain.valueobjects.MemberId;

public interface LoanRepository {

    // create + update
    Loan save(Loan loan);

    // tekil getir
    Optional<Loan> findById(LoanId id);

    // bir üyenin tüm loan’ları
    List<Loan> findByMemberId(MemberId memberId);

    // yeni ödünç verirken kontrol için
    List<Loan> findActiveByMemberId(MemberId memberId);

    // gecikmişleri topluca işlemek için (batch job)
    List<Loan> findOverdueLoans(LocalDate asOfDate);

    // kitap bazlı kontrol istersen (aynı kitap 2 kişide olmasın diyorsan)
    List<Loan> findActiveByBookId(com.TurkcellTakim7.loan_service.domain.valueobjects.BookId bookId);
}
