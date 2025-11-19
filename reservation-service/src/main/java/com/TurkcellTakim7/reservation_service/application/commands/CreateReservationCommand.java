package com.TurkcellTakim7.reservation_service.application.commands;

import java.util.UUID;

import com.TurkcellTakim7.reservation_service.application.core.command.Command;
import com.TurkcellTakim7.reservation_service.application.dtos.CreatedReservationResponse;

/**
 * Yeni rezervasyon oluşturma komutu.
 */
public record CreateReservationCommand(
                UUID memberId,
                UUID bookId) implements Command<CreatedReservationResponse> {
}
