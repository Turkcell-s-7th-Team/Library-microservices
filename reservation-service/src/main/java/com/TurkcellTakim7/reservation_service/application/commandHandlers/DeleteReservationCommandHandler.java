package com.TurkcellTakim7.reservation_service.application.commandHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.reservation_service.application.commands.DeleteReservationCommand;
import com.TurkcellTakim7.reservation_service.application.core.command.CommandHandler;
import com.TurkcellTakim7.reservation_service.domain.service.ReservationDomainService;

@Service
public class DeleteReservationCommandHandler
        implements CommandHandler<DeleteReservationCommand, Void> {

    private final ReservationDomainService reservationDomainService;

    public DeleteReservationCommandHandler(ReservationDomainService reservationDomainService) {
        this.reservationDomainService = reservationDomainService;
    }

    @Override
    public Void handle(DeleteReservationCommand command) {

        reservationDomainService.deleteReservation(command.reservationId());
        return null;
    }
}
