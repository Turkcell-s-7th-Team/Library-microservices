package com.TurkcellTakim7.staff_service.domain.services;

import java.util.List;

import com.TurkcellTakim7.staff_service.domain.entities.Staff;
import com.TurkcellTakim7.staff_service.domain.exceptions.StaffAlreadyExistsException;
import com.TurkcellTakim7.staff_service.domain.exceptions.StaffNotFoundException;
import com.TurkcellTakim7.staff_service.domain.repositories.StaffRepository;
import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffId;

public class StaffDomainService {

    private final StaffRepository staffRepository;

    public StaffDomainService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    /**
     * Yeni bir staff oluşturur ve phone benzersizliğini kontrol eder
     */
    public Staff createStaff(String name, String surname, String phone) {
        if (isPhoneAlreadyExists(phone)) {
            throw new StaffAlreadyExistsException(phone);
        }

        Staff staff = Staff.create(name, surname, phone);
        staffRepository.save(staff);
        return staff;
    }

    /**
     * Kayıtlı bir staff getirir
     */
    public Staff getStaff(StaffId staffId) {
        return staffRepository.findById(staffId)
                .orElseThrow(() -> new StaffNotFoundException(staffId));
    }

    /**
     * Kayıtlı tüm staffları getirir
     */
    public List<Staff> getStaffList() {
        return staffRepository.getAllStaff();
    }

    /**
     * Staff bilgilerini günceller
     */
    public Staff updateStaff(StaffId staffId, String name, String surname, String phone) {
        Staff existingStaff = staffRepository.findById(staffId)
                .orElseThrow(() -> new StaffNotFoundException(staffId));

        // Phone değişiyorsa benzersizlik kontrolü
        if (!existingStaff.getStaffPhone().equals(phone) && isPhoneAlreadyExists(phone)) {
            throw new StaffAlreadyExistsException(phone);
        }

        existingStaff.updatePersonalInfo(name, surname, phone);
        staffRepository.save(existingStaff);
        return existingStaff;
    }

    /**
     * Staff siler
     */
    public void deleteById(StaffId staffId) {
        getStaff(staffId);
        staffRepository.deleteById(staffId);
    }

    /**
     * Phone numarasının zaten kullanılıp kullanılmadığını kontrol eder
     */
    private boolean isPhoneAlreadyExists(String phone) {
        return staffRepository.existsByPhone(phone);
    }

}
