package com.TurkcellTakim7.reservation_service.application.mappers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.reservation_service.application.dtos.ReservationResponse;
import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;

/**
 * Genel Reservation -> ReservationResponse dönüşümü için mapper.
 * Get by id, list by member vs. bu mapper'ı kullanır.
 */
@Component
public class ReservationResponseMapper {

    public ReservationResponse toResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId().value(),
                reservation.getMemberId().value(),
                reservation.getBookId().value(),
                reservation.getQueuePosition(),
                reservation.getStatus(),
                reservation.getCreatedAt(),
                reservation.getUpdatedAt(),
                reservation.getPickupWindow() != null ? reservation.getPickupWindow().startAt() : null,
                reservation.getPickupWindow() != null ? reservation.getPickupWindow().expiresAt() : null);
    }
}
