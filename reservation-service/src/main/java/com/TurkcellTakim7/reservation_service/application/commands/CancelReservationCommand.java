package com.TurkcellTakim7.reservation_service.application.commands;

import com.TurkcellTakim7.reservation_service.application.core.command.Command;
import com.TurkcellTakim7.reservation_service.application.dtos.ReservationResponse;

public record CancelReservationCommand(
        String reservationId,
        String reason // opsiyonel
) implements Command<ReservationResponse> {
}
