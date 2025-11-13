package com.TurkcellTakim7.staff_service.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.TurkcellTakim7.staff_service.domain.entities.Staff;
import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffId;
import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffPhone;

public interface StaffRepository {

    Staff save(Staff staff);

    Optional<Staff> findById(StaffId staffId);

    Optional<Staff> findByPhone(StaffPhone phone);

    boolean existsByPhone(StaffPhone phone);

    List<Staff> getAllStaff();

    List<Staff> findByNameContaining(String name);

    List<Staff> findBySurnameContaining(String surname);

    void deleteById(StaffId staffId);
}
