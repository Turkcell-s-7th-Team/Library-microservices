package com.TurkcellTakim7.reservation_service.application.mappers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.reservation_service.application.dtos.ReservationResponse;
import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;

@Component
public class MarkReadyForPickupMapper {

    private final ReservationResponseMapper reservationResponseMapper;

    public MarkReadyForPickupMapper(ReservationResponseMapper reservationResponseMapper) {
        this.reservationResponseMapper = reservationResponseMapper;
    }

    public ReservationResponse toResponse(Reservation reservation) {
        return reservationResponseMapper.toResponse(reservation);
    }
}
