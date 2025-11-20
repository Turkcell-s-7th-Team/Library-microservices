package com.TurkcellTakim7.fine_service.web.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkcellTakim7.fine_service.application.commands.CreateFineCommand;
import com.TurkcellTakim7.fine_service.application.commands.DeleteFineCommand;
import com.TurkcellTakim7.fine_service.application.core.CommandHandler;
import com.TurkcellTakim7.fine_service.application.core.QueryHandler;
import com.TurkcellTakim7.fine_service.application.dto.CreatedFineResponse;
import com.TurkcellTakim7.fine_service.application.dto.FineResponse;
import com.TurkcellTakim7.fine_service.application.queries.GetFineByIdQuery;
import com.TurkcellTakim7.fine_service.application.queries.GetFineListQuery;
import com.TurkcellTakim7.fine_service.domain.valueobjects.FineId;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/fines")
public class FineController {

    private final CommandHandler<CreateFineCommand, CreatedFineResponse> createFineCommandHandler;
    private final QueryHandler<GetFineByIdQuery, FineResponse> getFineByIdQueryHandler;
    private final QueryHandler<GetFineListQuery, List<FineResponse>> getFineListQueryHandler;
    private final CommandHandler<DeleteFineCommand, Void> deleteFineCommandHandler;  


    public FineController(
            CommandHandler<CreateFineCommand, CreatedFineResponse> createFineCommandHandler,
            QueryHandler<GetFineByIdQuery, FineResponse> getFineByIdQueryHandler,
            CommandHandler<DeleteFineCommand, Void> deleteFineCommandHandler,              
            QueryHandler<GetFineListQuery, List<FineResponse>> getFineListQueryHandler) {

        this.createFineCommandHandler = createFineCommandHandler;
        this.getFineByIdQueryHandler = getFineByIdQueryHandler;
        this.deleteFineCommandHandler = deleteFineCommandHandler;                         
        this.getFineListQueryHandler = getFineListQueryHandler;
    }

    
    @GetMapping("/{id}")
    public FineResponse getFine(@PathVariable UUID id) {
        return getFineByIdQueryHandler.handle(new GetFineByIdQuery(new FineId(id)));
    }

    
    @GetMapping
    public List<FineResponse> getFineList() {
        return getFineListQueryHandler.handle(new GetFineListQuery());
    }

    
    @PostMapping
    public CreatedFineResponse createFine(@RequestBody @Valid CreateFineCommand command) {
        return createFineCommandHandler.handle(command);
    }

    @DeleteMapping("/{id}")
    public void deleteFine(@PathVariable UUID id) {
        deleteFineCommandHandler.handle(new DeleteFineCommand(id));
    }
}

