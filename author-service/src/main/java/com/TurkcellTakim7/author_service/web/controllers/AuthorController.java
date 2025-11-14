package com.TurkcellTakim7.author_service.web.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.TurkcellTakim7.author_service.application.commands.CreateAuthorCommand;
import com.TurkcellTakim7.author_service.application.commands.DeleteAuthorCommand;
import com.TurkcellTakim7.author_service.application.commands.UpdateAuthorCommand;
import com.TurkcellTakim7.author_service.application.core.Command.CommandHandler;
import com.TurkcellTakim7.author_service.application.core.Query.QueryHandler;
import com.TurkcellTakim7.author_service.application.dtos.AuthorResponse;
import com.TurkcellTakim7.author_service.application.dtos.CreatedAuthorResponse;
import com.TurkcellTakim7.author_service.application.queries.GetAuthorQuery;
import com.TurkcellTakim7.author_service.application.queries.ListAuthorsQuery;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/authors")
@Validated
public class AuthorController {

    private final CommandHandler<CreateAuthorCommand, CreatedAuthorResponse> createAuthorCommandHandler;
    private final QueryHandler<ListAuthorsQuery, List<AuthorResponse>> listAuthorsQueryHandler;
    private final QueryHandler<GetAuthorQuery, AuthorResponse> getAuthorQueryHandler;
    private final CommandHandler<UpdateAuthorCommand, AuthorResponse> updateAuthorCommandHandler;
    private final CommandHandler<DeleteAuthorCommand, Void> deleteAuthorCommandHandler;

    public AuthorController(CommandHandler<CreateAuthorCommand, CreatedAuthorResponse> createAuthorCommandHandler,
            QueryHandler<ListAuthorsQuery, List<AuthorResponse>> listAuthorsQueryHandler,
            QueryHandler<GetAuthorQuery, AuthorResponse> getAuthorQueryHandler,
            CommandHandler<UpdateAuthorCommand, AuthorResponse> updateAuthorCommandHandler,
            CommandHandler<DeleteAuthorCommand, Void> deleteAuthorCommandHandler) {
        this.createAuthorCommandHandler = createAuthorCommandHandler;
        this.listAuthorsQueryHandler = listAuthorsQueryHandler;
        this.getAuthorQueryHandler = getAuthorQueryHandler;
        this.updateAuthorCommandHandler = updateAuthorCommandHandler;
        this.deleteAuthorCommandHandler = deleteAuthorCommandHandler;
    }

    @GetMapping("/{id}")
    public AuthorResponse getAuthorById(@PathVariable UUID id) {
        return getAuthorQueryHandler.handle(new GetAuthorQuery(id));
    }

    @GetMapping
    public List<AuthorResponse> getAuthors(
            @RequestParam(defaultValue = "0") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        ListAuthorsQuery query = new ListAuthorsQuery(pageIndex, pageSize);
        return listAuthorsQueryHandler.handle(query);
    }

    @PostMapping()
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public CreatedAuthorResponse createAuthor(@Valid @RequestBody CreateAuthorCommand command) {
        return createAuthorCommandHandler.handle(command);
    }

    @PutMapping("/{id}")
    public AuthorResponse updateAuthor(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateAuthorCommand command) {

        UpdateAuthorCommand fixedCommand = new UpdateAuthorCommand(
                id,
                command.name(),
                command.surname(),
                command.email(),
                command.phoneNumber());

        return updateAuthorCommandHandler.handle(fixedCommand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable UUID id) {
        deleteAuthorCommandHandler.handle(new DeleteAuthorCommand(id));
    }

}
