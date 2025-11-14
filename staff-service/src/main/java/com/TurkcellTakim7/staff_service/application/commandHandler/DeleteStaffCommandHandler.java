package com.TurkcellTakim7.staff_service.application.commandHandler;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.staff_service.application.commands.DeleteStaffCommand;
import com.TurkcellTakim7.staff_service.application.core.CommandHandler;
import com.TurkcellTakim7.staff_service.domain.services.StaffDomainService;
import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffId;

@Component
public class DeleteStaffCommandHandler implements CommandHandler<DeleteStaffCommand, Void> {

    private final StaffDomainService staffDomainService;

    public DeleteStaffCommandHandler(StaffDomainService staffDomainService) {
        this.staffDomainService = staffDomainService;
    }

    @Override
    public Void handle(DeleteStaffCommand command) {
        StaffId staffId = new StaffId(command.staffId());
        staffDomainService.deleteById(staffId);
        return null;
    }
}
