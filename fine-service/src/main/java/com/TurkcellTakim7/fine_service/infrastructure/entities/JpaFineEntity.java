package com.TurkcellTakim7.fine_service.infrastructure.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fines")
public class JpaFineEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "member_id", nullable = false)
    private UUID memberId;

    @Column(name = "loan_id", nullable = false)
    private UUID loanId;

    @Column(name = "fine_type", nullable = false, length = 30)
    private String fineType;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "paid_at")
    private LocalDate paidAt;

    public JpaFineEntity() {}

    public JpaFineEntity(UUID id,
                         UUID memberId,
                         UUID loanId,
                         String fineType,
                         BigDecimal amount,
                         LocalDate createdAt,
                         String status,
                         LocalDate paidAt) {

        this.id = id;
        this.memberId = memberId;
        this.loanId = loanId;
        this.fineType = fineType;
        this.amount = amount;
        this.createdAt = createdAt;
        this.status = status;
        this.paidAt = paidAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getMemberId() { return memberId; }
    public void setMemberId(UUID memberId) { this.memberId = memberId; }

    public UUID getLoanId() { return loanId; }
    public void setLoanId(UUID loanId) { this.loanId = loanId; }

    public String getFineType() { return fineType; }
    public void setFineType(String fineType) { this.fineType = fineType; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getPaidAt() { return paidAt; }
    public void setPaidAt(LocalDate paidAt) { this.paidAt = paidAt; }
}
