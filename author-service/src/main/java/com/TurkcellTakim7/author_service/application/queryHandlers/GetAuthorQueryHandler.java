package com.TurkcellTakim7.author_service.application.queryHandlers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.author_service.application.core.Query.QueryHandler;
import com.TurkcellTakim7.author_service.application.dtos.AuthorResponse;
import com.TurkcellTakim7.author_service.application.mappers.AuthorResponseMapper;
import com.TurkcellTakim7.author_service.application.queries.GetAuthorQuery;
import com.TurkcellTakim7.author_service.domain.entities.Author;
import com.TurkcellTakim7.author_service.domain.services.AuthorDomainService;
import com.TurkcellTakim7.author_service.domain.valueobjects.AuthorId;

@Component
public class GetAuthorQueryHandler implements QueryHandler<GetAuthorQuery, AuthorResponse> {

    private final AuthorDomainService authorDomainService;
    private final AuthorResponseMapper authorResponseMapper;

    public GetAuthorQueryHandler(AuthorDomainService authorDomainService, AuthorResponseMapper authorResponseMapper) {
        this.authorDomainService = authorDomainService;
        this.authorResponseMapper = authorResponseMapper;
    }

    @Override
    public AuthorResponse handle(GetAuthorQuery query) {
        Author author = authorDomainService.getAuthor(new AuthorId(query.id()));

        return authorResponseMapper.toResponse(author);
    }

}
