package com.TurkcellTakim7.fine_service.application.queryHandlers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.fine_service.application.core.QueryHandler;
import com.TurkcellTakim7.fine_service.application.dto.FineResponse;
import com.TurkcellTakim7.fine_service.application.mappers.GetFineMapper;
import com.TurkcellTakim7.fine_service.application.queries.GetFineByIdQuery;
import com.TurkcellTakim7.fine_service.domain.entities.Fine;
import com.TurkcellTakim7.fine_service.domain.services.FineDomainService;

@Component
public class GetFineByIdQueryHandler implements QueryHandler<GetFineByIdQuery, FineResponse> {

    private final GetFineMapper getFineMapper;
    private final FineDomainService fineDomainService;

    public GetFineByIdQueryHandler(GetFineMapper getFineMapper,
                                   FineDomainService fineDomainService) {
        this.getFineMapper = getFineMapper;
        this.fineDomainService = fineDomainService;
    }

    @Override
    public FineResponse handle(GetFineByIdQuery query) {
        Fine fine = fineDomainService.getFine(query.fineId());
        return getFineMapper.toResponse(fine);
    }
}
