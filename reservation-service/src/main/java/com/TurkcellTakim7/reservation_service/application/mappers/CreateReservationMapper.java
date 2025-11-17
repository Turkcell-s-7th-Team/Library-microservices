package com.TurkcellTakim7.reservation_service.application.mappers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.reservation_service.application.dtos.CreatedReservationResponse;
import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;

@Component
public class CreateReservationMapper {

    public CreatedReservationResponse toCreatedReservationResponse(Reservation reservation) {
        return new CreatedReservationResponse(
                reservation.getId().value(),
                reservation.getMemberId().value(),
                reservation.getBookId().value(),
                reservation.getQueuePosition(),
                reservation.getStatus(),
                reservation.getCreatedAt());
    }
}
