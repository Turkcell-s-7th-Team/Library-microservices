package com.TurkcellTakim7.staff_service.domain.events;

import java.time.LocalDateTime;

import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffId;

public class StaffUpdatedEvent {

    private final StaffId staffId;
    private final String name;
    private final String surname;
    private final String staffPhone;
    private final LocalDateTime occurredOn;

    public StaffUpdatedEvent(StaffId staffId, String name, String surname, String staffPhone) {
        this.staffId = staffId;
        this.name = name;
        this.surname = surname;
        this.staffPhone = staffPhone;
        this.occurredOn = LocalDateTime.now();
    }

    public StaffId getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return staffPhone;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String toString() {
        return "StaffUpdatedEvent{" +
                "staffId=" + staffId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", staffPhone='" + staffPhone + '\'' +
                ", occurredOn=" + occurredOn +
                '}';
    }
}
