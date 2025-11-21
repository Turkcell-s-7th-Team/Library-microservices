package com.TurkcellTakim7.reservation_service.infrastructure.messaging.listeners;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.TurkcellTakim7.reservation_service.domain.service.ReservationDomainService;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.reservation_service.infrastructure.messaging.events.LoanReturnedEvent;

@Configuration
public class LoanEventsConsumerConfig {

    @Bean
    public Consumer<LoanReturnedEvent> loanReturnedConsumer(
            ReservationDomainService reservationDomainService) {

        return event -> {
            BookId bookId = new BookId(event.bookId());
            reservationDomainService.markNextReservationReadyForPickupAndReorder(bookId);
        };
    }
}
