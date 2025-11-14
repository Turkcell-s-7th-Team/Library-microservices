package com.TurkcellTakim7.loan_service.application.queryHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.loan_service.application.core.query.QueryHandler;
import com.TurkcellTakim7.loan_service.application.dtos.LoanResponse;
import com.TurkcellTakim7.loan_service.application.mappers.LoanResponseMapper;
import com.TurkcellTakim7.loan_service.application.queries.GetLoanByIdQuery;
import com.TurkcellTakim7.loan_service.domain.entities.Loan;
import com.TurkcellTakim7.loan_service.domain.services.LoanDomainService;

@Service
public class GetLoanByIdQueryHandler implements QueryHandler<GetLoanByIdQuery, LoanResponse> {

    private final LoanDomainService loanDomainService;
    private final LoanResponseMapper loanResponseMapper;

    public GetLoanByIdQueryHandler(LoanDomainService loanDomainService,
            LoanResponseMapper loanResponseMapper) {
        this.loanDomainService = loanDomainService;
        this.loanResponseMapper = loanResponseMapper;
    }

    @Override
    public LoanResponse handle(GetLoanByIdQuery query) {

        Loan loan = loanDomainService.getLoanById(query.loanId());

        return loanResponseMapper.toResponse(loan);
    }
}
