package com.TurkcellTakim7.reservation_service.application.queries;

import java.util.List;
import java.util.UUID;

import com.TurkcellTakim7.reservation_service.application.core.query.Query;
import com.TurkcellTakim7.reservation_service.application.dtos.ReservationResponse;

public record GetReservationsByMemberQuery(
                UUID memberId) implements Query<List<ReservationResponse>> {
}
