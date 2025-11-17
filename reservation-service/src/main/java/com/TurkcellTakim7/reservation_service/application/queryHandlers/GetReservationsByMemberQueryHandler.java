package com.TurkcellTakim7.reservation_service.application.queryHandlers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.reservation_service.application.core.query.QueryHandler;
import com.TurkcellTakim7.reservation_service.application.dtos.ReservationResponse;
import com.TurkcellTakim7.reservation_service.application.mappers.ReservationResponseMapper;
import com.TurkcellTakim7.reservation_service.application.queries.GetReservationsByMemberQuery;
import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;
import com.TurkcellTakim7.reservation_service.domain.service.ReservationDomainService;

@Service
public class GetReservationsByMemberQueryHandler
        implements QueryHandler<GetReservationsByMemberQuery, List<ReservationResponse>> {

    private final ReservationDomainService reservationDomainService;
    private final ReservationResponseMapper reservationResponseMapper;

    public GetReservationsByMemberQueryHandler(ReservationDomainService reservationDomainService,
            ReservationResponseMapper reservationResponseMapper) {
        this.reservationDomainService = reservationDomainService;
        this.reservationResponseMapper = reservationResponseMapper;
    }

    @Override
    public List<ReservationResponse> handle(GetReservationsByMemberQuery query) {

        List<Reservation> reservations = reservationDomainService.getReservationsByMemberId(query.memberId());

        return reservations.stream()
                .map(reservationResponseMapper::toResponse)
                .toList();
    }
}
