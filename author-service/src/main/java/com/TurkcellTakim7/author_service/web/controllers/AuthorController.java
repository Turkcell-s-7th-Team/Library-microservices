package com.TurkcellTakim7.author_service.web.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkcellTakim7.author_service.application.commands.CreateAuthorCommand;
import com.TurkcellTakim7.author_service.application.core.Command.CommandHandler;
import com.TurkcellTakim7.author_service.application.dtos.CreatedAuthorResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final CommandHandler<CreateAuthorCommand, CreatedAuthorResponse> createAuthorCommandHandler;

    public AuthorController(CommandHandler<CreateAuthorCommand, CreatedAuthorResponse> createAuthorCommandHandler) {
        this.createAuthorCommandHandler = createAuthorCommandHandler;
    }

    @PostMapping()
    public CreatedAuthorResponse createAuthor(@Valid @RequestBody CreateAuthorCommand command) {
        return createAuthorCommandHandler.handle(command);
    }

}
