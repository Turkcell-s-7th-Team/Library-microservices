package com.TurkcellTakim7.loan_service.application.queries;

import com.TurkcellTakim7.loan_service.application.core.query.Query;
import com.TurkcellTakim7.loan_service.application.dtos.LoanResponse;

public record GetLoanByIdQuery(String loanId) implements Query<LoanResponse> {

}
