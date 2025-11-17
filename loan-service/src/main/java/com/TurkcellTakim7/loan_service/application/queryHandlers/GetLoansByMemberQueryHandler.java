package com.TurkcellTakim7.loan_service.application.queryHandlers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.loan_service.application.core.query.QueryHandler;
import com.TurkcellTakim7.loan_service.application.dtos.LoanResponse;
import com.TurkcellTakim7.loan_service.application.mappers.LoanResponseMapper;
import com.TurkcellTakim7.loan_service.application.queries.GetLoansByMemberQuery;
import com.TurkcellTakim7.loan_service.domain.entities.Loan;
import com.TurkcellTakim7.loan_service.domain.services.LoanDomainService;

@Service
public class GetLoansByMemberQueryHandler implements QueryHandler<GetLoansByMemberQuery, List<LoanResponse>> {

    private final LoanDomainService loanDomainService;
    private final LoanResponseMapper loanResponseMapper;

    public GetLoansByMemberQueryHandler(LoanDomainService loanDomainService,
            LoanResponseMapper loanResponseMapper) {
        this.loanDomainService = loanDomainService;
        this.loanResponseMapper = loanResponseMapper;
    }

    @Override
    public List<LoanResponse> handle(GetLoansByMemberQuery query) {

        List<Loan> loans = loanDomainService.getLoansByMemberId(query.memberId());

        return loanResponseMapper.toResponseList(loans);
    }
}
