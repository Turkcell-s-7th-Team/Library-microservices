package com.TurkcellTakim7.staff_service.domain.events;

import java.time.LocalDateTime;

import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffId;

public class StaffDeletedEvent {

    private final StaffId staffId;
    private final LocalDateTime occurredOn;

    public StaffDeletedEvent(StaffId staffId) {
        this.staffId = staffId;
        this.occurredOn = LocalDateTime.now();
    }

    public StaffId getStaffId() {
        return staffId;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String toString() {
        return "StaffDeletedEvent{" +
                "staffId=" + staffId +
                ", occurredOn=" + occurredOn +
                '}';
    }
}
