
package com.TurkcellTakim7.staff_service.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.staff_service.domain.entities.Staff;
import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffId;
import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffPhone;
import com.TurkcellTakim7.staff_service.infrastructure.entities.JpaStaffEntity;

@Component
public class StaffEntityMapper {

    public JpaStaffEntity toEntity(Staff staff) {
        JpaStaffEntity entity = new JpaStaffEntity();
        entity.setId(staff.getStaffId().value());
        entity.setName(staff.getName());
        entity.setSurname(staff.getSurname());
        entity.setStaffPhone(staff.getStaffPhone().toString());
        return entity;
    }

    public Staff toDomain(JpaStaffEntity entity) {
        return Staff.rehydrate(
                new StaffId(entity.getId()),
                entity.getName(),
                entity.getSurname(),
                new StaffPhone(entity.getStaffPhone())
        );
    }
}
