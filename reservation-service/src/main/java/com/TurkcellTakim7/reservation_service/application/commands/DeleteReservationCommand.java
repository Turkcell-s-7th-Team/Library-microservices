package com.TurkcellTakim7.reservation_service.application.commands;

import com.TurkcellTakim7.reservation_service.application.core.command.Command;

public record DeleteReservationCommand(
        String reservationId) implements Command<Void> {
}
