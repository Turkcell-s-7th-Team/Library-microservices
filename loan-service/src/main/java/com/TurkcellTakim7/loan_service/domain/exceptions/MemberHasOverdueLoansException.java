package com.TurkcellTakim7.loan_service.domain.exceptions;

public class MemberHasOverdueLoansException extends DomainException {

    public MemberHasOverdueLoansException(String memberId) {
        super("Member has overdue loans: " + memberId);
    }
}
