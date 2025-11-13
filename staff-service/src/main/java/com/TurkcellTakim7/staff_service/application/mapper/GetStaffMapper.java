package com.TurkcellTakim7.staff_service.application.mapper;

import org.springframework.stereotype.Component;
import com.TurkcellTakim7.staff_service.application.dto.StaffResponse;
import com.TurkcellTakim7.staff_service.domain.entities.Staff;

@Component
public class GetStaffMapper {

    public StaffResponse toResponse(Staff staff) {
        return new StaffResponse(
                staff.getStaffId().value(),  
                staff.getName(),
                staff.getSurname(),
                staff.getStaffPhone().toString()
        );
    }
}
