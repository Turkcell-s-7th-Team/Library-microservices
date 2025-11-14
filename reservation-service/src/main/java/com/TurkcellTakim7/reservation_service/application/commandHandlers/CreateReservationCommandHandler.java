package com.TurkcellTakim7.reservation_service.application.commandHandlers;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.reservation_service.application.commands.CreateReservationCommand;
import com.TurkcellTakim7.reservation_service.application.core.command.CommandHandler;
import com.TurkcellTakim7.reservation_service.application.dtos.CreatedReservationResponse;
import com.TurkcellTakim7.reservation_service.application.mappers.CreateReservationMapper;
import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;
import com.TurkcellTakim7.reservation_service.domain.service.ReservationDomainService;

@Service
public class CreateReservationCommandHandler
        implements CommandHandler<CreateReservationCommand, CreatedReservationResponse> {

    private final ReservationDomainService reservationDomainService;
    private final CreateReservationMapper createReservationMapper;

    public CreateReservationCommandHandler(ReservationDomainService reservationDomainService,
            CreateReservationMapper createReservationMapper) {
        this.reservationDomainService = reservationDomainService;
        this.createReservationMapper = createReservationMapper;
    }

    @Override
    public CreatedReservationResponse handle(CreateReservationCommand command) {

        Reservation reservation = reservationDomainService.createReservation(
                command.memberId(),
                command.bookId());

        return createReservationMapper.toCreatedReservationResponse(reservation);
    }
}
