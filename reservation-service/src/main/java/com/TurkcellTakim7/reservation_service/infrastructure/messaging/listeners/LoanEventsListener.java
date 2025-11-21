package com.TurkcellTakim7.reservation_service.infrastructure.messaging.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.TurkcellTakim7.reservation_service.domain.service.ReservationDomainService;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.reservation_service.infrastructure.messaging.events.LoanCreatedEvent;

@Component
public class LoanEventsListener {

    private final ReservationDomainService reservationDomainService;

    public LoanEventsListener(ReservationDomainService reservationDomainService) {
        this.reservationDomainService = reservationDomainService;
    }

    @KafkaListener(topics = "loan.events.loan-created", // ⬅️ LoanEventPublisher hangi topic'e gönderiyorsa AYNI
            groupId = "reservation-service" // ⬅️ Aşağıdaki YAML'daki consumer.group-id ile aynı
    )
    public void onLoanCreated(LoanCreatedEvent event) {

        System.out.println("🔥 LoanCreatedEvent received for bookId: " + event.bookId());

        // Loan oluşturulduğunda, bir sonraki rezervasyonu WAITING_FOR_PICKUP yap
        reservationDomainService.markNextReservationReadyForPickup(
                new BookId(event.bookId()));

        System.out.println("✔ Next reservation marked as WAITING_FOR_PICKUP for book: " + event.bookId());
    }
}
