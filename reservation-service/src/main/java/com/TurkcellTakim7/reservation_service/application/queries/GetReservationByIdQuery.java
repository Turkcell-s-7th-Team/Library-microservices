package com.TurkcellTakim7.reservation_service.application.queries;

import com.TurkcellTakim7.reservation_service.application.core.query.Query;
import com.TurkcellTakim7.reservation_service.application.dtos.ReservationResponse;

public record GetReservationByIdQuery(
        String reservationId) implements Query<ReservationResponse> {
}
