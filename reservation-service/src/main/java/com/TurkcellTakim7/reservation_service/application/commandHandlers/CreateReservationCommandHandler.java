package com.TurkcellTakim7.reservation_service.application.commandHandlers;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.TurkcellTakim7.reservation_service.application.commands.CreateReservationCommand;
import com.TurkcellTakim7.reservation_service.application.core.command.CommandHandler;
import com.TurkcellTakim7.reservation_service.application.dtos.CreatedReservationResponse;
import com.TurkcellTakim7.reservation_service.application.mappers.CreateReservationMapper;
import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;
import com.TurkcellTakim7.reservation_service.domain.exceptions.ReservationValidationException;
import com.TurkcellTakim7.reservation_service.domain.service.ReservationDomainService;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.reservation_service.infrastructure.adapter.feignClients.bookClient.BookClient;
import com.TurkcellTakim7.reservation_service.infrastructure.adapter.feignClients.bookClient.BookResponse;
import com.TurkcellTakim7.reservation_service.infrastructure.adapter.feignClients.memberClient.MemberClient;
import com.TurkcellTakim7.reservation_service.infrastructure.adapter.feignClients.memberClient.MemberResponse;

@Service
public class CreateReservationCommandHandler
        implements CommandHandler<CreateReservationCommand, CreatedReservationResponse> {

    private final ReservationDomainService reservationDomainService;
    private final CreateReservationMapper createReservationMapper;
    private final BookClient bookClient;
    private final MemberClient memberClient;

    public CreateReservationCommandHandler(
            ReservationDomainService reservationDomainService,
            CreateReservationMapper createReservationMapper,
            BookClient bookClient,
            MemberClient memberClient) {
        this.reservationDomainService = reservationDomainService;
        this.createReservationMapper = createReservationMapper;
        this.bookClient = bookClient;
        this.memberClient = memberClient;
    }

    @Override
    public CreatedReservationResponse handle(CreateReservationCommand command) {

        UUID memberId = command.memberId();
        UUID bookId = command.bookId();

        MemberResponse member = memberClient.getMemberById(memberId);
        if (member == null) {
            throw new ReservationValidationException("Member not found with id: " + memberId);
        }

        if ("BANNED".equalsIgnoreCase(member.membershipLevel())) {
            throw new ReservationValidationException(
                    "BANNED members cannot create reservations. memberId=" + memberId);
        }

        BookResponse book = bookClient.getBookById(bookId);
        if (book == null) {
            throw new ReservationValidationException("Book not found with id: " + bookId);
        }

        if (book.availableCopies() <= 0) {
            throw new ReservationValidationException(
                    "Book has no available copies for reservation. bookId=" + bookId);
        }

        MemberId memberIdVo = new MemberId(memberId);
        BookId bookIdVo = new BookId(bookId);

        Reservation reservation = reservationDomainService.createReservation(memberIdVo, bookIdVo);

        return createReservationMapper.toCreatedReservationResponse(reservation);
    }
}
