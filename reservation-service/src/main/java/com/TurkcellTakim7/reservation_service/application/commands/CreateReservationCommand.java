package com.TurkcellTakim7.reservation_service.application.commands;

import com.TurkcellTakim7.reservation_service.application.core.command.Command;
import com.TurkcellTakim7.reservation_service.application.dtos.CreatedReservationResponse;

/**
 * Yeni rezervasyon oluşturma komutu.
 */
public record CreateReservationCommand(
        String memberId,
        String bookId
) implements Command<CreatedReservationResponse> {
}
