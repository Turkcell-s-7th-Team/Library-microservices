package com.TurkcellTakim7.reservation_service.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.reservation_service.domain.entity.Reservation;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.PickupWindow;
import com.TurkcellTakim7.reservation_service.domain.valueobjects.ReservationId;
import com.TurkcellTakim7.reservation_service.infrastructure.entities.ReservationJpaEntity;

@Component
public class ReservationDataMapper {

    public ReservationJpaEntity toEntity(Reservation reservation) {
        ReservationJpaEntity entity = new ReservationJpaEntity();

        entity.setId(reservation.getId().value());
        entity.setMemberId(reservation.getMemberId().value());
        entity.setBookId(reservation.getBookId().value());
        entity.setQueuePosition(reservation.getQueuePosition());
        entity.setStatus(reservation.getStatus());
        entity.setCreatedAt(reservation.getCreatedAt());
        entity.setUpdatedAt(reservation.getUpdatedAt());

        if (reservation.getPickupWindow() != null) {
            entity.setPickupStartAt(reservation.getPickupWindow().startAt());
            entity.setPickupExpiresAt(reservation.getPickupWindow().expiresAt());
        } else {
            entity.setPickupStartAt(null);
            entity.setPickupExpiresAt(null);
        }

        return entity;
    }

    public Reservation toDomain(ReservationJpaEntity entity) {

        ReservationId reservationId = new ReservationId(entity.getId());
        MemberId memberId = new MemberId(entity.getMemberId());
        BookId bookId = new BookId(entity.getBookId());

        PickupWindow pickupWindow = null;
        if (entity.getPickupStartAt() != null &&
                entity.getPickupExpiresAt() != null) {

            pickupWindow = new PickupWindow(
                    entity.getPickupStartAt(),
                    entity.getPickupExpiresAt());
        }

        return Reservation.restore(
                reservationId,
                memberId,
                bookId,
                entity.getQueuePosition(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                pickupWindow);
    }
}
