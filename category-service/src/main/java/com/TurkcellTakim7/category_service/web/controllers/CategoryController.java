package com.TurkcellTakim7.category_service.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkcellTakim7.category_service.application.commandHandlers.CreateCategoryCommandHandler;
import com.TurkcellTakim7.category_service.application.commandHandlers.DeleteCategoryCommandHandler;
import com.TurkcellTakim7.category_service.application.commandHandlers.UpdateCategoryCommandHandler;
import com.TurkcellTakim7.category_service.application.commands.CreateCategoryCommand;
import com.TurkcellTakim7.category_service.application.commands.DeleteCategoryCommand;
import com.TurkcellTakim7.category_service.application.commands.UpdateCategoryCommand;
import com.TurkcellTakim7.category_service.application.dtos.CategoryRequest;
import com.TurkcellTakim7.category_service.application.dtos.CategoryResponse;
import com.TurkcellTakim7.category_service.application.queries.GetAllCategoriesQuery;
import com.TurkcellTakim7.category_service.application.queries.GetCategoryByIdQuery;
import com.TurkcellTakim7.category_service.application.queryHandlers.GetAllCategoriesQueryHandler;
import com.TurkcellTakim7.category_service.application.queryHandlers.GetCategoryByIdQueryHandler;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CreateCategoryCommandHandler createHandler;
    private final UpdateCategoryCommandHandler updateHandler;
    private final DeleteCategoryCommandHandler deleteHandler;

    private final GetAllCategoriesQueryHandler getAllHandler;
    private final GetCategoryByIdQueryHandler getByIdHandler;

    public CategoryController(
            CreateCategoryCommandHandler createHandler,
            UpdateCategoryCommandHandler updateHandler,
            DeleteCategoryCommandHandler deleteHandler,
            GetAllCategoriesQueryHandler getAllHandler,
            GetCategoryByIdQueryHandler getByIdHandler) {
        this.createHandler = createHandler;
        this.updateHandler = updateHandler;
        this.deleteHandler = deleteHandler;
        this.getAllHandler = getAllHandler;
        this.getByIdHandler = getByIdHandler;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest req) {
        var cmd = new CreateCategoryCommand(req.getName());
        var response = createHandler.handle(cmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(
            @PathVariable String id,
            @RequestBody CategoryRequest req) {

        var cmd = new UpdateCategoryCommand(id, req.getName(), req.isActive());
        var response = updateHandler.handle(cmd);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponse> delete(@PathVariable String id) {
        var cmd = new DeleteCategoryCommand(id);
        var response = deleteHandler.handle(cmd);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        var response = getAllHandler.handle(new GetAllCategoriesQuery());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable String id) {
        var response = getByIdHandler.handle(new GetCategoryByIdQuery(id));
        return ResponseEntity.ok(response);
    }
}
