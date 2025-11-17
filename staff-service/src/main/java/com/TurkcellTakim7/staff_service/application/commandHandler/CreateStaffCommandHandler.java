package com.TurkcellTakim7.staff_service.application.commandHandler;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.staff_service.application.commands.CreateStaffCommand;
import com.TurkcellTakim7.staff_service.application.core.CommandHandler;
import com.TurkcellTakim7.staff_service.application.dto.CreatedStaffResponse;
import com.TurkcellTakim7.staff_service.application.mapper.CreateStaffMapper;
import com.TurkcellTakim7.staff_service.domain.entities.Staff;
import com.TurkcellTakim7.staff_service.domain.services.StaffDomainService;
import com.TurkcellTakim7.staff_service.domain.repositories.StaffRepository;


@Component
public class CreateStaffCommandHandler implements CommandHandler<CreateStaffCommand, CreatedStaffResponse> {

    private final CreateStaffMapper createStaffMapper;
    private final StaffDomainService staffDomainService;
    private final StaffRepository staffRepository;

    public CreateStaffCommandHandler(CreateStaffMapper createStaffMapper, StaffDomainService staffDomainService,
    StaffRepository staffRepository) {
        this.createStaffMapper = createStaffMapper;
        this.staffDomainService = staffDomainService;
        this.staffRepository = staffRepository;
    }

    @Override
    public CreatedStaffResponse handle(CreateStaffCommand command) {
        Staff staff = staffDomainService.createStaff(
                command.name(),
                command.surname(),
                command.staffPhone()
        );
        staff = staffRepository.save(staff);

        return createStaffMapper.toResponse(staff);
    }
}
