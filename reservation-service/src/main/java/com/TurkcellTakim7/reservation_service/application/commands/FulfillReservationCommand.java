package com.TurkcellTakim7.reservation_service.application.commands;

import com.TurkcellTakim7.reservation_service.application.core.command.Command;
import com.TurkcellTakim7.reservation_service.application.dtos.ReservationResponse;

/**
 * Rezervasyonun FULFILLED yapılması (kitap teslim alındı) komutu.
 */
public record FulfillReservationCommand(
        String reservationId,
        String staffId // opsiyonel
) implements Command<ReservationResponse> {
}
