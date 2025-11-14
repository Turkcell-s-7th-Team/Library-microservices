package com.TurkcellTakim7.reservation_service.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkcellTakim7.reservation_service.application.commandHandlers.CancelReservationCommandHandler;
import com.TurkcellTakim7.reservation_service.application.commandHandlers.CreateReservationCommandHandler;
import com.TurkcellTakim7.reservation_service.application.commandHandlers.DeleteReservationCommandHandler;
import com.TurkcellTakim7.reservation_service.application.commandHandlers.FulfillReservationCommandHandler;
import com.TurkcellTakim7.reservation_service.application.commandHandlers.MarkNextReservationReadyForPickupCommandHandler;
import com.TurkcellTakim7.reservation_service.application.commands.CancelReservationCommand;
import com.TurkcellTakim7.reservation_service.application.commands.CreateReservationCommand;
import com.TurkcellTakim7.reservation_service.application.commands.DeleteReservationCommand;
import com.TurkcellTakim7.reservation_service.application.commands.FulfillReservationCommand;
import com.TurkcellTakim7.reservation_service.application.commands.MarkNextReservationReadyForPickupCommand;
import com.TurkcellTakim7.reservation_service.application.dtos.CancelReservationRequest;
import com.TurkcellTakim7.reservation_service.application.dtos.CreateReservationRequest;
import com.TurkcellTakim7.reservation_service.application.dtos.CreatedReservationResponse;
import com.TurkcellTakim7.reservation_service.application.dtos.FulfillReservationRequest;
import com.TurkcellTakim7.reservation_service.application.dtos.ReservationResponse;
import com.TurkcellTakim7.reservation_service.application.queries.GetReservationByIdQuery;
import com.TurkcellTakim7.reservation_service.application.queries.GetReservationsByMemberQuery;
import com.TurkcellTakim7.reservation_service.application.queryHandlers.GetReservationByIdQueryHandler;
import com.TurkcellTakim7.reservation_service.application.queryHandlers.GetReservationsByMemberQueryHandler;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final CreateReservationCommandHandler createReservationCommandHandler;
    private final CancelReservationCommandHandler cancelReservationCommandHandler;
    private final FulfillReservationCommandHandler fulfillReservationCommandHandler;
    private final DeleteReservationCommandHandler deleteReservationCommandHandler;
    private final MarkNextReservationReadyForPickupCommandHandler markNextReservationReadyForPickupCommandHandler;
    private final GetReservationByIdQueryHandler getReservationByIdQueryHandler;
    private final GetReservationsByMemberQueryHandler getReservationsByMemberQueryHandler;

    public ReservationController(CreateReservationCommandHandler createReservationCommandHandler,
            CancelReservationCommandHandler cancelReservationCommandHandler,
            FulfillReservationCommandHandler fulfillReservationCommandHandler,
            DeleteReservationCommandHandler deleteReservationCommandHandler,
            MarkNextReservationReadyForPickupCommandHandler markNextReservationReadyForPickupCommandHandler,
            GetReservationByIdQueryHandler getReservationByIdQueryHandler,
            GetReservationsByMemberQueryHandler getReservationsByMemberQueryHandler) {
        this.createReservationCommandHandler = createReservationCommandHandler;
        this.cancelReservationCommandHandler = cancelReservationCommandHandler;
        this.fulfillReservationCommandHandler = fulfillReservationCommandHandler;
        this.deleteReservationCommandHandler = deleteReservationCommandHandler;
        this.markNextReservationReadyForPickupCommandHandler = markNextReservationReadyForPickupCommandHandler;
        this.getReservationByIdQueryHandler = getReservationByIdQueryHandler;
        this.getReservationsByMemberQueryHandler = getReservationsByMemberQueryHandler;
    }

    // 1) create reservation
    @PostMapping
    public ResponseEntity<CreatedReservationResponse> create(@RequestBody CreateReservationRequest request) {

        CreatedReservationResponse response = createReservationCommandHandler.handle(
                new CreateReservationCommand(
                        request.getMemberId(),
                        request.getBookId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 2) get reservation by id
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getById(@PathVariable("id") String id) {

        ReservationResponse response = getReservationByIdQueryHandler.handle(
                new GetReservationByIdQuery(id));

        return ResponseEntity.ok(response);
    }

    // 3) get reservations by member
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<ReservationResponse>> getByMember(@PathVariable("memberId") String memberId) {

        List<ReservationResponse> response = getReservationsByMemberQueryHandler.handle(
                new GetReservationsByMemberQuery(memberId));

        return ResponseEntity.ok(response);
    }

    // 4) cancel reservation
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable("id") String id,
            @RequestBody(required = false) CancelReservationRequest request) {

        cancelReservationCommandHandler.handle(
                new CancelReservationCommand(
                        id,
                        request != null ? request.getReason() : null));

        return ResponseEntity.noContent().build();
    }

    // 5) fulfill reservation (member picked up the book)
    @PostMapping("/{id}/fulfill")
    public ResponseEntity<Void> fulfill(@PathVariable("id") String id,
            @RequestBody(required = false) FulfillReservationRequest request) {

        fulfillReservationCommandHandler.handle(
                new FulfillReservationCommand(
                        id,
                        request != null ? request.getStaffId() : null));

        return ResponseEntity.noContent().build();
    }

    // 6) delete reservation (hard delete - opsiyonel endpoint)
    @PostMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {

        deleteReservationCommandHandler.handle(
                new DeleteReservationCommand(id));

        return ResponseEntity.noContent().build();
    }

    // 7) mark next reservation as WAITING_FOR_PICKUP by bookId
    @PostMapping("/book/{bookId}/ready")
    public ResponseEntity<ReservationResponse> markNextReadyForPickup(
            @PathVariable("bookId") String bookId) {

        ReservationResponse response = markNextReservationReadyForPickupCommandHandler.handle(
                new MarkNextReservationReadyForPickupCommand(bookId));

        // Kuyrukta PENDING rezervasyon yoksa 204 dön
        if (response == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }
}
