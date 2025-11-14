package com.TurkcellTakim7.reservation_service.application.commandHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.reservation_service.application.commands.CancelReservationCommand;
import com.TurkcellTakim7.reservation_service.application.core.command.CommandHandler;
import com.TurkcellTakim7.reservation_service.application.dtos.ReservationResponse;
import com.TurkcellTakim7.reservation_service.application.mappers.CancelReservationMapper;
import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;
import com.TurkcellTakim7.reservation_service.domain.service.ReservationDomainService;

@Service
public class CancelReservationCommandHandler
        implements CommandHandler<CancelReservationCommand, ReservationResponse> {

    private final ReservationDomainService reservationDomainService;
    private final CancelReservationMapper cancelReservationMapper;

    public CancelReservationCommandHandler(ReservationDomainService reservationDomainService,
            CancelReservationMapper cancelReservationMapper) {
        this.reservationDomainService = reservationDomainService;
        this.cancelReservationMapper = cancelReservationMapper;
    }

    @Override
    public ReservationResponse handle(CancelReservationCommand command) {

        // reason istersen log için kullanılır
        reservationDomainService.cancelReservation(command.reservationId());

        Reservation updated = reservationDomainService.getReservationById(command.reservationId());
        return cancelReservationMapper.toResponse(updated);
    }
}
