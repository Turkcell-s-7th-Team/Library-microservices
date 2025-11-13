package com.TurkcellTakim7.staff_service.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkcellTakim7.staff_service.application.commands.CreateStaffCommand;
import com.TurkcellTakim7.staff_service.application.commands.DeleteStaffCommand;
import com.TurkcellTakim7.staff_service.application.commands.UpdateStaffCommand;
import com.TurkcellTakim7.staff_service.application.core.CommandHandler;
import com.TurkcellTakim7.staff_service.application.core.QueryHandler;
import com.TurkcellTakim7.staff_service.application.dto.CreatedStaffResponse;
import com.TurkcellTakim7.staff_service.application.dto.StaffResponse;
import com.TurkcellTakim7.staff_service.application.dto.UpdateStaffRequest;
import com.TurkcellTakim7.staff_service.application.dto.UpdatedStaffResponse;
import com.TurkcellTakim7.staff_service.application.queries.GetStaffListQuery;
import com.TurkcellTakim7.staff_service.application.queries.GetStaffQuery;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/staffs")
public class StaffController {


    private final CommandHandler<CreateStaffCommand, CreatedStaffResponse> createStaffCommandHandler;
    private final CommandHandler<UpdateStaffCommand, UpdatedStaffResponse> updateStaffCommandHandler;
    private final CommandHandler<DeleteStaffCommand, Void> deleteStaffCommandHandler;
    private final QueryHandler<GetStaffQuery, StaffResponse> getStaffQueryHandler;
    private final QueryHandler<GetStaffListQuery, List<StaffResponse>> getStaffListQueryHandler;

    public StaffController(
            CommandHandler<CreateStaffCommand, CreatedStaffResponse> createStaffCommandHandler,
            CommandHandler<UpdateStaffCommand, UpdatedStaffResponse> updateStaffCommandHandler,
            QueryHandler<GetStaffQuery, StaffResponse> getStaffQueryHandler,
            QueryHandler<GetStaffListQuery, List<StaffResponse>> getStaffListQueryHandler,
            CommandHandler<DeleteStaffCommand, Void> deleteStaffCommandHandler) {
             
        this.createStaffCommandHandler = createStaffCommandHandler;
        this.updateStaffCommandHandler = updateStaffCommandHandler;
        this.getStaffQueryHandler = getStaffQueryHandler;
        this.getStaffListQueryHandler = getStaffListQueryHandler;
        this.deleteStaffCommandHandler = deleteStaffCommandHandler;
    }

    @GetMapping("/{id}")
    public StaffResponse getStaff(@PathVariable UUID id) {
        return getStaffQueryHandler.handle(new GetStaffQuery(id));
    }

    @GetMapping
    public List<StaffResponse> getStaffList(GetStaffListQuery query) {
        return getStaffListQueryHandler.handle(query);
    }

    @PostMapping
    public CreatedStaffResponse createStaff(@RequestBody CreateStaffCommand command) {
        return createStaffCommandHandler.handle(command);
    }

    @PutMapping("/{id}")
    public UpdatedStaffResponse updateStaff(@PathVariable UUID id, 
    @RequestBody @Valid UpdateStaffRequest request) {
        UpdateStaffCommand command = new UpdateStaffCommand(
                id,
                request.name(),
                request.surname(),
                request.staffPhone()
        );
        return updateStaffCommandHandler.handle(command);
    }

    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable UUID id) {
      deleteStaffCommandHandler.handle(new DeleteStaffCommand(id));
    }
}