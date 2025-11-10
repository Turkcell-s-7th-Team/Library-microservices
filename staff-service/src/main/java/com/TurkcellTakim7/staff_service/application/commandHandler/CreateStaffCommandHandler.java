package com.TurkcellTakim7.staff_service.application.commandHandler;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.staff_service.application.commands.CreateStaffCommand;
import com.TurkcellTakim7.staff_service.application.core.CommandHandler;
import com.TurkcellTakim7.staff_service.application.dto.CreatedStaffResponse;
import com.TurkcellTakim7.staff_service.application.mapper.CreateStaffMapper;
import com.TurkcellTakim7.staff_service.domain.entities.Staff;
import com.TurkcellTakim7.staff_service.domain.services.StaffDomainService;

@Component
public class CreateStaffCommandHandler implements CommandHandler<CreateStaffCommand, CreatedStaffResponse> {

    private final CreateStaffMapper createStaffMapper;
    private final StaffDomainService staffDomainService;

    public CreateStaffCommandHandler(CreateStaffMapper createStaffMapper, StaffDomainService staffDomainService) {
        this.createStaffMapper = createStaffMapper;
        this.staffDomainService = staffDomainService;
    }

    @Override
    public CreatedStaffResponse handle(CreateStaffCommand command) {
        Staff staff = staffDomainService.createStaff(
                command.name(),
                command.surname(),
                command.staffPhone()
        );

        return createStaffMapper.toResponse(staff);
    }
}
