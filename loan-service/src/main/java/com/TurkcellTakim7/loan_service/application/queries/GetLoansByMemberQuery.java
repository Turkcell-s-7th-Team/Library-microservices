package com.TurkcellTakim7.loan_service.application.queries;

import java.util.List;

import com.TurkcellTakim7.loan_service.application.core.query.Query;
import com.TurkcellTakim7.loan_service.application.dtos.LoanResponse;

public record GetLoansByMemberQuery(String memberId)
        implements Query<List<LoanResponse>> {
}
