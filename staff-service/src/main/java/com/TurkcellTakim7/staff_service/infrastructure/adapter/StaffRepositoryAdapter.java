package com.TurkcellTakim7.staff_service.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.staff_service.domain.entities.Staff;
import com.TurkcellTakim7.staff_service.domain.repositories.StaffRepository;
import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffId;
import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffPhone;
import com.TurkcellTakim7.staff_service.infrastructure.entities.JpaStaffEntity;
import com.TurkcellTakim7.staff_service.infrastructure.mapper.StaffEntityMapper;
import com.TurkcellTakim7.staff_service.infrastructure.repository.SpringDataStaffRepository;

@Component
public class StaffRepositoryAdapter implements StaffRepository {

    private final SpringDataStaffRepository springDataStaffRepository;
    private final StaffEntityMapper staffEntityMapper;

    public StaffRepositoryAdapter(SpringDataStaffRepository springDataStaffRepository,
                                  StaffEntityMapper staffEntityMapper) {
        this.springDataStaffRepository = springDataStaffRepository;
        this.staffEntityMapper = staffEntityMapper;
    }

    @Override
    public Staff save(Staff staff) {
        JpaStaffEntity entity = staffEntityMapper.toEntity(staff);
        entity = springDataStaffRepository.save(entity);
        return staffEntityMapper.toDomain(entity);
    }

    @Override
    public Optional<Staff> findById(StaffId staffId) {
        return springDataStaffRepository.findById(staffId.value())
                .map(staffEntityMapper::toDomain);
    }

    @Override
    public Optional<Staff> findByPhone(StaffPhone phone) {
        return springDataStaffRepository.findByPhoneNumber(phone.value())
                .map(staffEntityMapper::toDomain);
    }

    @Override
    public List<Staff> getAllStaff() {
        return springDataStaffRepository.findAll()
                .stream()
                .map(staffEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Staff> findByNameContaining(String name) {
        return springDataStaffRepository.findByNameContaining(name)
                .stream()
                .map(staffEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(StaffId staffId) {
        springDataStaffRepository.deleteById(staffId.value());
    }

    @Override
    public boolean existsByPhone(String phone) {
        return springDataStaffRepository.existsByPhone(phone);
    }
}
