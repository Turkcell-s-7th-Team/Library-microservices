package com.TurkcellTakim7.loan_service.infrastructure.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "loans")
public class LoanEntity {

    @Id
    private UUID id;

    private UUID memberId;
    private UUID bookId;
    private UUID staffId;

    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    private String status;

    public LoanEntity() {
    }

    public LoanEntity(UUID id,
            UUID memberId,
            UUID bookId,
            UUID staffId,
            LocalDate loanDate,
            LocalDate dueDate,
            LocalDate returnDate,
            String status) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.staffId = staffId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public UUID getStaffId() {
        return staffId;
    }

    public void setStaffId(UUID staffId) {
        this.staffId = staffId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
