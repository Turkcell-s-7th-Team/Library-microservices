package com.TurkcellTakim7.loan_service.domain.entities;

import com.TurkcellTakim7.loan_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.loan_service.domain.valueobjects.LoanId;
import com.TurkcellTakim7.loan_service.domain.enums.LoanStatus;
import com.TurkcellTakim7.loan_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.loan_service.domain.valueobjects.StaffId;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {

    private final LoanId id;
    private final MemberId memberId;
    private final BookId bookId;
    private final StaffId staffId;

    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    private LoanStatus status;

    // sadece bu sınıf içinde kullanılacak ctor
    private Loan(LoanId id,
                 MemberId memberId,
                 BookId bookId,
                 StaffId staffId,
                 LocalDate loanDate,
                 LocalDate dueDate,
                 LocalDate returnDate,
                 LoanStatus status) {

        this.id = Objects.requireNonNull(id, "loan id cannot be null");
        this.memberId = Objects.requireNonNull(memberId, "member id cannot be null");
        this.bookId = Objects.requireNonNull(bookId, "book id cannot be null");
        this.staffId = Objects.requireNonNull(staffId, "staff id cannot be null");
        this.loanDate = Objects.requireNonNull(loanDate, "loan date cannot be null");
        this.dueDate = Objects.requireNonNull(dueDate, "due date cannot be null");
        this.returnDate = returnDate;
        this.status = Objects.requireNonNull(status, "status cannot be null");
    }

    public static Loan create(MemberId memberId,
                              BookId bookId,
                              StaffId staffId,
                              LocalDate loanDate,
                              LocalDate dueDate) {

        return new Loan(
                LoanId.generateLoanId(),
                memberId,
                bookId,
                staffId,
                loanDate,
                dueDate,
                null,
                LoanStatus.ACTIVE
        );
    }

    // persistence katmanından domain'e geri toplarken (rehydrate)
    public static Loan rehydrate(LoanId id,
                                 MemberId memberId,
                                 BookId bookId,
                                 StaffId staffId,
                                 LocalDate loanDate,
                                 LocalDate dueDate,
                                 LocalDate returnDate,
                                 LoanStatus status) {

        return new Loan(
                id,
                memberId,
                bookId,
                staffId,
                loanDate,
                dueDate,
                returnDate,
                status
        );
    }

    public void returnBook(LocalDate returnDate) {
        if (this.status == LoanStatus.RETURNED) {
            return; // istersen exception da atabilirsin
        }
        this.returnDate = returnDate;
        this.status = LoanStatus.RETURNED;
    }

    public void markOverdueIfNeeded(LocalDate today) {
        if (this.status == LoanStatus.ACTIVE && today.isAfter(this.dueDate)) {
            this.status = LoanStatus.OVERDUE;
        }
    }

    public void extendDueDate(int days) {
        if (this.status != LoanStatus.ACTIVE) {
            throw new IllegalStateException("Only ACTIVE loans can be extended");
        }
        this.dueDate = this.dueDate.plusDays(days);
    }

    // --------- getters ---------

    public LoanId id() {
        return id;
    }

    public MemberId memberId() {
        return memberId;
    }

    public BookId bookId() {
        return bookId;
    }

    public StaffId staffId() {
        return staffId;
    }

    public LocalDate loanDate() {
        return loanDate;
    }

    public LocalDate dueDate() {
        return dueDate;
    }

    public LocalDate returnDate() {
        return returnDate;
    }

    public LoanStatus status() {
        return status;
    }
}
