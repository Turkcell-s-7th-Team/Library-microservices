package com.TurkcellTakim7.reservation_service.application.commands;

import java.util.UUID;

import com.TurkcellTakim7.reservation_service.application.core.command.Command;

public record DeleteReservationCommand(
                UUID reservationId) implements Command<Void> {
}
