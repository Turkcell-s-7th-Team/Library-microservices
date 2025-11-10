package com.TurkcellTakim7.author_service.application.queries;

import java.util.List;

import com.TurkcellTakim7.author_service.application.core.Query.Query;
import com.TurkcellTakim7.author_service.application.dtos.AuthorResponse;

import jakarta.validation.constraints.Min;

public record ListAuthorsQuery(
        @Min(0) Integer pageIndex,
        @Min(1) Integer pageSize) implements Query<List<AuthorResponse>> {

}
