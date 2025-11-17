package com.TurkcellTakim7.reservation_service.application.commands;

import com.TurkcellTakim7.reservation_service.application.core.command.Command;
import com.TurkcellTakim7.reservation_service.application.dtos.ReservationResponse;

/**
 * Verilen kitap için kuyruktaki PENDING durumundaki
 * ilk rezervasyonu WAITING_FOR_PICKUP yapmak için komut.
 */
public record MarkNextReservationReadyForPickupCommand(
        String bookId) implements Command<ReservationResponse> {
}
