package com.TurkcellTakim7.reservation_service.application.commandHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.reservation_service.application.commands.FulfillReservationCommand;
import com.TurkcellTakim7.reservation_service.application.core.command.CommandHandler;
import com.TurkcellTakim7.reservation_service.application.dtos.ReservationResponse;
import com.TurkcellTakim7.reservation_service.application.mappers.FulfillReservationMapper;
import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;
import com.TurkcellTakim7.reservation_service.domain.service.ReservationDomainService;

@Service
public class FulfillReservationCommandHandler
        implements CommandHandler<FulfillReservationCommand, ReservationResponse> {

    private final ReservationDomainService reservationDomainService;
    private final FulfillReservationMapper fulfillReservationMapper;

    public FulfillReservationCommandHandler(ReservationDomainService reservationDomainService,
            FulfillReservationMapper fulfillReservationMapper) {
        this.reservationDomainService = reservationDomainService;
        this.fulfillReservationMapper = fulfillReservationMapper;
    }

    @Override
    public ReservationResponse handle(FulfillReservationCommand command) {

        // staffId şimdilik kullanılmıyor ama komutta duruyor
        reservationDomainService.fulfillReservation(command.reservationId());

        Reservation updated = reservationDomainService.getReservationById(command.reservationId());
        return fulfillReservationMapper.toResponse(updated);
    }
}
