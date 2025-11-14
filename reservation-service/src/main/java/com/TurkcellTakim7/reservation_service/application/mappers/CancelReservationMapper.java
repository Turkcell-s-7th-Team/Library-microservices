package com.TurkcellTakim7.reservation_service.application.mappers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.reservation_service.application.dtos.ReservationResponse;
import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;

@Component
public class CancelReservationMapper {

    private final ReservationResponseMapper responseMapper;

    public CancelReservationMapper(ReservationResponseMapper responseMapper) {
        this.responseMapper = responseMapper;
    }

    public ReservationResponse toResponse(Reservation reservation) {
        return responseMapper.toResponse(reservation);
    }
}
