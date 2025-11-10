package com.TurkcellTakim7.staff_service.application.commandHandler;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.staff_service.application.commands.UpdateStaffCommand;
import com.TurkcellTakim7.staff_service.application.core.CommandHandler;
import com.TurkcellTakim7.staff_service.application.dto.UpdatedStaffResponse;
import com.TurkcellTakim7.staff_service.application.mapper.UpdateStaffMapper;
import com.TurkcellTakim7.staff_service.domain.entities.Staff;
import com.TurkcellTakim7.staff_service.domain.services.StaffDomainService;
import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffId;

@Component
public class UpdateStaffCommandHandler implements CommandHandler<UpdateStaffCommand, UpdatedStaffResponse> {

    private final StaffDomainService staffDomainService;
    private final UpdateStaffMapper updateStaffMapper;

    public UpdateStaffCommandHandler(StaffDomainService staffDomainService, UpdateStaffMapper updateStaffMapper) {
        this.staffDomainService = staffDomainService;
        this.updateStaffMapper = updateStaffMapper;
    }

    @Override
    public UpdatedStaffResponse handle(UpdateStaffCommand command) {
        StaffId staffId = new StaffId(command.staffId());

        Staff updatedStaff = staffDomainService.updateStaff(
                staffId,
                command.name(),
                command.surname(),
                command.staffPhone());

        return updateStaffMapper.toResponse(updatedStaff);
    }
}
