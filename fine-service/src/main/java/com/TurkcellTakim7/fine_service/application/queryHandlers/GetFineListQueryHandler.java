package com.TurkcellTakim7.fine_service.application.queryHandlers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.fine_service.application.core.QueryHandler;
import com.TurkcellTakim7.fine_service.application.dto.FineResponse;
import com.TurkcellTakim7.fine_service.application.mappers.GetFineMapper;
import com.TurkcellTakim7.fine_service.application.queries.GetFineListQuery;
import com.TurkcellTakim7.fine_service.domain.entities.Fine;
import com.TurkcellTakim7.fine_service.domain.services.FineDomainService;

@Component
public class GetFineListQueryHandler implements QueryHandler<GetFineListQuery, List<FineResponse>> {

    private final FineDomainService fineDomainService;
    private final GetFineMapper getFineMapper;

    public GetFineListQueryHandler(FineDomainService fineDomainService,
                                   GetFineMapper getFineMapper) {
        this.fineDomainService = fineDomainService;
        this.getFineMapper = getFineMapper;
    }

    @Override
    public List<FineResponse> handle(GetFineListQuery query) {
        List<Fine> fineList = fineDomainService.getFineList();

        return fineList.stream()
                .map(getFineMapper::toResponse)
                .toList();
    }
}
