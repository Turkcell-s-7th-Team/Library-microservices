package com.TurkcellTakim7.staff_service.application.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.staff_service.application.dto.UpdatedStaffResponse;
import com.TurkcellTakim7.staff_service.domain.entities.Staff;
import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffId;
import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffPhone;


@Component
public class UpdateStaffMapper {

    public UpdatedStaffResponse toResponse(Staff staff) {
        return new UpdatedStaffResponse(
                toUuid(staff),
                staff.getName(),
                staff.getSurname(),
                staff.getStaffPhone().toString()
        );
    }

    private UUID toUuid(Staff staff) {
        return staff.getStaffId() != null ? staff.getStaffId().value() : null;
    }

    public Staff toDomain(UpdatedStaffResponse response) {
        return Staff.rehydrate(
                new StaffId(response.id()),
                response.name(),
                response.surname(),
                new StaffPhone(response.staffPhone())
        );
    }
}
