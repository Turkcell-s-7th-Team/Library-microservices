package com.TurkcellTakim7.author_service.web.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TurkcellTakim7.author_service.application.commands.CreateAuthorCommand;
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

    public AuthorController(CommandHandler<CreateAuthorCommand, CreatedAuthorResponse> createAuthorCommandHandler,
            QueryHandler<ListAuthorsQuery, List<AuthorResponse>> listAuthorsQueryHandler,
            QueryHandler<GetAuthorQuery, AuthorResponse> getAuthorQueryHandler) {
        this.createAuthorCommandHandler = createAuthorCommandHandler;
        this.listAuthorsQueryHandler = listAuthorsQueryHandler;
        this.getAuthorQueryHandler = getAuthorQueryHandler;
    }

    @GetMapping("/{id}")
    public AuthorResponse getAuthorById(@PathVariable UUID id) {
        return getAuthorQueryHandler.handle(new GetAuthorQuery(id));
    }

    @GetMapping
    public List<AuthorResponse> getAuthors(
            @RequestParam Integer pageIndex,
            @RequestParam Integer pageSize) {

        ListAuthorsQuery query = new ListAuthorsQuery(pageIndex, pageSize);
        return listAuthorsQueryHandler.handle(query);
    }

    @PostMapping()
    public CreatedAuthorResponse createAuthor(@Valid @RequestBody CreateAuthorCommand command) {
        return createAuthorCommandHandler.handle(command);
    }

}
