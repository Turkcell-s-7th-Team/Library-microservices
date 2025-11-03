package com.TurkcellTakim7.author_service.application.queryHandlers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.author_service.application.core.Query.QueryHandler;
import com.TurkcellTakim7.author_service.application.dtos.AuthorResponse;
import com.TurkcellTakim7.author_service.application.mappers.AuthorResponseMapper;
import com.TurkcellTakim7.author_service.application.queries.ListAuthorsQuery;
import com.TurkcellTakim7.author_service.domain.repositories.AuthorRepository;

@Component
public class ListAuthorsQueryHandler implements QueryHandler<ListAuthorsQuery, List<AuthorResponse>> {

    private final AuthorRepository authorRepository;
    private final AuthorResponseMapper AuthorResponseMapper;

    public ListAuthorsQueryHandler(AuthorRepository authorRepository,
            com.TurkcellTakim7.author_service.application.mappers.AuthorResponseMapper authorResponseMapper) {
        this.authorRepository = authorRepository;
        AuthorResponseMapper = authorResponseMapper;
    }

    @Override
    public List<AuthorResponse> handle(ListAuthorsQuery query) {
        return authorRepository
                .getAllAuthors(query.pageIndex(), query.pageSize())
                .stream()
                .map(AuthorResponseMapper::toResponse)
                .toList();
    }

}
