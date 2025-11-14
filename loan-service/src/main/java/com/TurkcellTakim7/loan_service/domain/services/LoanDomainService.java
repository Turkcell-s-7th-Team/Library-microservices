package com.TurkcellTakim7.loan_service.domain.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.loan_service.domain.entities.Loan;
import com.TurkcellTakim7.loan_service.domain.exceptions.LoanNotFoundException;
import com.TurkcellTakim7.loan_service.domain.repositories.LoanRepository;
import com.TurkcellTakim7.loan_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.loan_service.domain.valueobjects.LoanId;
import com.TurkcellTakim7.loan_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.loan_service.domain.valueobjects.StaffId;

@Component
public class LoanDomainService {

    private final LoanRepository loanRepository;

    public LoanDomainService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    /**
     * Yeni bir loan oluşturur.
     * Şimdilik kendi veritabanımızdan kontrol ediyoruz:
     * - Aynı kitap ACTIVE durumdayken tekrar ödünç verilmesin.
     */
    public Loan createLoan(String memberIdRaw,
            String bookIdRaw,
            String staffIdRaw,
            LocalDate loanDate,
            LocalDate dueDate) {

        MemberId memberId = new MemberId(UUID.fromString(memberIdRaw));
        BookId bookId = new BookId(UUID.fromString(bookIdRaw));
        StaffId staffId = new StaffId(UUID.fromString(staffIdRaw));

        // kitap zaten aktif ödünçte mi?
        boolean bookAlreadyLoaned = !loanRepository.findActiveByBookId(bookId).isEmpty();
        if (bookAlreadyLoaned) {
            // ileride BookAlreadyLoanedException diye ayrı exception açabilirsin
            throw new IllegalStateException("Book is already loaned: " + bookId.value());
        }

        Loan loan = Loan.create(
                memberId,
                bookId,
                staffId,
                loanDate,
                dueDate);

        return loanRepository.save(loan);
    }

    /**
     * Loan'u id'ye göre getirir, yoksa exception atar.
     */
    public Loan getLoanById(String loanIdRaw) {
        LoanId loanId = new LoanId(UUID.fromString(loanIdRaw));
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException(loanId.value()));
    }

    /**
     * Bir üyenin tüm loan'larını getirir.
     */
    public List<Loan> getLoansByMemberId(String memberIdRaw) {
        MemberId memberId = new MemberId(UUID.fromString(memberIdRaw));
        return loanRepository.findByMemberId(memberId);
    }

    /**
     * Loan'u iade eder.
     */
    public void returnLoan(String loanIdRaw, LocalDate returnDate) {
        LoanId loanId = new LoanId(UUID.fromString(loanIdRaw));

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException(loanId.value()));

        LocalDate effectiveReturnDate = (returnDate != null) ? returnDate : LocalDate.now();
        loan.returnBook(effectiveReturnDate);

        loanRepository.save(loan);
    }

    /**
     * Loan'un vadesini uzatır.
     */
    public void extendLoan(String loanIdRaw, int days) {
        LoanId loanId = new LoanId(UUID.fromString(loanIdRaw));

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException(loanId.value()));

        loan.extendDueDate(days);

        loanRepository.save(loan);
    }
}
