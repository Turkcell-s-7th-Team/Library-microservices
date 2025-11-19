package com.TurkcellTakim7.reservation_service.application.commandHandlers;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.reservation_service.application.commands.CancelReservationCommand;
import com.TurkcellTakim7.reservation_service.application.core.command.CommandHandler;
import com.TurkcellTakim7.reservation_service.application.dtos.ReservationResponse;
import com.TurkcellTakim7.reservation_service.application.mappers.CancelReservationMapper;
import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;
import com.TurkcellTakim7.reservation_service.domain.service.ReservationDomainService;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.ReservationId;

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

        UUID reservationIdRaw = command.reservationId();
        ReservationId reservationId = new ReservationId(reservationIdRaw);

        // reason istersen log, audit vs. için kullanılabilir
        reservationDomainService.cancelReservation(reservationId);

        Reservation updated = reservationDomainService.getReservationById(reservationId);
        return cancelReservationMapper.toResponse(updated);
    }
}
