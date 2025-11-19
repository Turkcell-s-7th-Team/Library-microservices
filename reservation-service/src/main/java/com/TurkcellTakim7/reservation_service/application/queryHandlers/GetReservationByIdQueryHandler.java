package com.TurkcellTakim7.reservation_service.application.queryHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.reservation_service.application.core.query.QueryHandler;
import com.TurkcellTakim7.reservation_service.application.dtos.ReservationResponse;
import com.TurkcellTakim7.reservation_service.application.mappers.ReservationResponseMapper;
import com.TurkcellTakim7.reservation_service.application.queries.GetReservationByIdQuery;
import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;
import com.TurkcellTakim7.reservation_service.domain.service.ReservationDomainService;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.ReservationId;

@Service
public class GetReservationByIdQueryHandler
                implements QueryHandler<GetReservationByIdQuery, ReservationResponse> {

        private final ReservationDomainService reservationDomainService;
        private final ReservationResponseMapper reservationResponseMapper;

        public GetReservationByIdQueryHandler(ReservationDomainService reservationDomainService,
                        ReservationResponseMapper reservationResponseMapper) {
                this.reservationDomainService = reservationDomainService;
                this.reservationResponseMapper = reservationResponseMapper;
        }

        @Override
        public ReservationResponse handle(GetReservationByIdQuery query) {

                ReservationId id = new ReservationId(query.reservationId());

                Reservation reservation = reservationDomainService.getReservationById(id);
                return reservationResponseMapper.toResponse(reservation);
        }
}
