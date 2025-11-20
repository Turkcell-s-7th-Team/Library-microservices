package com.TurkcellTakim7.fine_service.domain.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.TurkcellTakim7.fine_service.domain.enums.FineStatus;
import com.TurkcellTakim7.fine_service.domain.enums.FineType;
import com.TurkcellTakim7.fine_service.domain.valueobjects.Amount;
import com.TurkcellTakim7.fine_service.domain.valueobjects.FineId;
import com.TurkcellTakim7.fine_service.domain.valueobjects.LoanId;
import com.TurkcellTakim7.fine_service.domain.valueobjects.MemberId;

public class Fine {

    private final FineId fineId;
    private final MemberId memberId;
    private final LoanId loanId;
    private final FineType fineType;
    private final LocalDate createdAt;

    private Amount amount;
    private FineStatus status;
    private LocalDate paidAt;

    public Fine(FineId fineId,
                MemberId memberId,
                LoanId loanId,
                FineType fineType,
                Amount amount,
                LocalDate createdAt,
                FineStatus status,
                LocalDate paidAt) {

        this.fineId = Objects.requireNonNull(fineId, "FineId cannot be null");
        this.memberId = Objects.requireNonNull(memberId, "MemberId cannot be null");
        this.loanId = Objects.requireNonNull(loanId, "LoanId cannot be null");
        this.fineType = Objects.requireNonNull(fineType, "FineType cannot be null");
        this.amount = Objects.requireNonNull(amount, "Amount cannot be null");
        this.createdAt = Objects.requireNonNull(createdAt, "CreatedAt cannot be null");
        this.status = Objects.requireNonNull(status, "FineStatus cannot be null");
        this.paidAt = paidAt; // ödenmemişse null olabilir
    }

    // Factory methods
    public static Fine create(MemberId memberId,
                              LoanId loanId,
                              FineType fineType,
                              Amount amount,
                              LocalDate createdAt) {

        return new Fine(
                FineId.generate(),
                memberId,
                loanId,
                fineType,
                amount,
                createdAt,
                FineStatus.UNPAID,
                null
        );
    }

    public static Fine rehydrate(FineId fineId,
                                 MemberId memberId,
                                 LoanId loanId,
                                 FineType fineType,
                                 Amount amount,
                                 LocalDate createdAt,
                                 FineStatus status,
                                 LocalDate paidAt) {

        return new Fine(
                fineId,
                memberId,
                loanId,
                fineType,
                amount,
                createdAt,
                status,
                paidAt
        );
    }

    // Getters
    public FineId getFineId() {
        return fineId;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public LoanId getLoanId() {
        return loanId;
    }

    public FineType getFineType() {
        return fineType;
    }

    public Amount getAmount() {
        return amount;
    }

    public FineStatus getStatus() {
        return status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getPaidAt() {
        return paidAt;
    }

    // Business methods
    public void updateAmount(Amount newAmount) {
        this.amount = Objects.requireNonNull(newAmount, "Amount cannot be null");
    }

    public void markAsPaid(LocalDate paymentDate) {
        if (this.status == FineStatus.PAID) {
            throw new IllegalStateException("Fine is already paid");
        }
        this.status = FineStatus.PAID;
        this.paidAt = Objects.requireNonNull(paymentDate, "PaymentDate cannot be null");
    }

    public boolean isPaid() {
        return this.status == FineStatus.PAID;
    }

    public boolean isUnpaid() {
        return this.status == FineStatus.UNPAID;
    }

    // equals / hashCode sadece kimliğe göre
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Fine fine = (Fine) o;
        return Objects.equals(fineId, fine.fineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fineId);
    }

    @Override
    public String toString() {
        return "Fine{" +
                "fineId=" + fineId +
                ", memberId=" + memberId +
                ", loanId=" + loanId +
                ", fineType=" + fineType +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}