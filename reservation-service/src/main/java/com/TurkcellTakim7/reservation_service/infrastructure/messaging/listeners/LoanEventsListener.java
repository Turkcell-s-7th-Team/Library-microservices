package com.TurkcellTakim7.reservation_service.infrastructure.messaging.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.TurkcellTakim7.reservation_service.domain.service.ReservationDomainService;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.reservation_service.infrastructure.messaging.events.LoanCreatedEvent;
import com.TurkcellTakim7.reservation_service.infrastructure.messaging.events.LoanReturnedEvent;

@Component
public class LoanEventsListener {

    private final ReservationDomainService reservationDomainService;

    public LoanEventsListener(ReservationDomainService reservationDomainService) {
        this.reservationDomainService = reservationDomainService;
    }

    @KafkaListener(topics = "loancreated-events", // loanCreated-out-0 destination
            groupId = "reservation-service")
    public void onLoanCreated(LoanCreatedEvent event) {
        System.out.println("🔥 LoanCreatedEvent received for bookId: " + event.bookId());

        reservationDomainService.markNextReservationReadyForPickup(
                new BookId(event.bookId()));

        System.out.println("✔ Next reservation marked as WAITING_FOR_PICKUP for book: " + event.bookId());
    }

    @KafkaListener(topics = "loanreturn-events", // loanReturned-out-0 destination
            groupId = "reservation-service")
    public void onLoanReturned(LoanReturnedEvent event) {
        System.out.println("🔥 LoanReturnedEvent received for bookId: " + event.bookId());

        reservationDomainService.markNextReservationReadyForPickupAndReorder(
                new BookId(event.bookId()));

        System.out.println("✔ Next reservation reordered after return for book: " + event.bookId());
    }
}
