package com.TurkcellTakim7.reservation_service.application.commandHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.reservation_service.application.commands.MarkNextReservationReadyForPickupCommand;
import com.TurkcellTakim7.reservation_service.application.core.command.CommandHandler;
import com.TurkcellTakim7.reservation_service.application.dtos.ReservationResponse;
import com.TurkcellTakim7.reservation_service.application.mappers.MarkReadyForPickupMapper;
import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;
import com.TurkcellTakim7.reservation_service.domain.service.ReservationDomainService;

@Service
public class MarkNextReservationReadyForPickupCommandHandler
        implements CommandHandler<MarkNextReservationReadyForPickupCommand, ReservationResponse> {

    private final ReservationDomainService reservationDomainService;
    private final MarkReadyForPickupMapper markReadyForPickupMapper;

    public MarkNextReservationReadyForPickupCommandHandler(ReservationDomainService reservationDomainService,
            MarkReadyForPickupMapper markReadyForPickupMapper) {
        this.reservationDomainService = reservationDomainService;
        this.markReadyForPickupMapper = markReadyForPickupMapper;
    }

    @Override
    public ReservationResponse handle(MarkNextReservationReadyForPickupCommand command) {

        Reservation reservation = reservationDomainService.markNextReservationReadyForPickup(command.bookId());

        // Kuyrukta PENDING yoksa domain null döndürüyor
        if (reservation == null) {
            return null; // Controller 204 No Content dönecek
        }

        return markReadyForPickupMapper.toResponse(reservation);
    }
}
