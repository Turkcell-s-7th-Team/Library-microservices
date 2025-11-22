package com.TurkcellTakim7.reservation_service.infrastructure.messaging.listeners;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.TurkcellTakim7.reservation_service.domain.service.ReservationDomainService;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.reservation_service.infrastructure.messaging.events.LoanCreatedEvent;
import com.TurkcellTakim7.reservation_service.infrastructure.messaging.events.LoanReturnedEvent;

@Configuration
public class LoanEventsConsumerConfig {

    @Bean
    public Consumer<LoanCreatedEvent> loanCreatedReservation(
            ReservationDomainService reservationDomainService) {
        return event -> {
            System.out.println("📥 [RES] LoanCreatedEvent consumed, bookId = " + event.bookId());
            reservationDomainService.markNextReservationReadyForPickup(
                    new BookId(event.bookId()));
        };
    }

    @Bean
    public Consumer<LoanReturnedEvent> loanReturnedReservation(
            ReservationDomainService reservationDomainService) {
        return event -> {
            System.out.println("📥 [RES] LoanReturnedEvent consumed, bookId = " + event.bookId());
            reservationDomainService.markNextReservationReadyForPickupAndReorder(
                    new BookId(event.bookId()));
        };
    }
}
