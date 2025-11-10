package com.TurkcellTakim7.staff_service.application.mapper;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.staff_service.application.commands.CreateStaffCommand;
import com.TurkcellTakim7.staff_service.application.dto.CreatedStaffResponse;
import com.TurkcellTakim7.staff_service.domain.entities.Staff;

@Component
public class CreateStaffMapper {

    public Staff toDomain(CreateStaffCommand command) {
        return Staff.create(
                command.name(),
                command.surname(),
                command.staffPhone()
        );
    }

    public CreatedStaffResponse toResponse(Staff staff) {
        return new CreatedStaffResponse(
                staff.getName(),
                staff.getSurname(),
                staff.getStaffPhone()
        );
    }
}
