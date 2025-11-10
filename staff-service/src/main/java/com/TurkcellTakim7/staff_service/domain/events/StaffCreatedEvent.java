package com.TurkcellTakim7.staff_service.domain.events;

import java.time.LocalDateTime;

import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffId;
import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffName;
import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffPhone;
import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffSurname;

public class StaffCreatedEvent {

    private final StaffId staffId;
    private final String name;
    private final String surname;
    private final String staffPhone;
    private final LocalDateTime occurredOn;

    public StaffCreatedEvent(StaffId staffId, String name, String surname, String staffPhone) {
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

    public String getStaffPhone() {
        return staffPhone;
    }
    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String toString() {
        return "StaffCreatedEvent{" +
                "staffId=" + staffId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", staffPhone='" + staffPhone + '\'' +
                ", occurredOn=" + occurredOn +
                '}';
    }
}
