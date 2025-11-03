package com.TurkcellTakim7.author_service.application.queries;

import java.util.UUID;

import com.TurkcellTakim7.author_service.application.core.Query.Query;
import com.TurkcellTakim7.author_service.application.dtos.AuthorResponse;

public record GetAuthorQuery(UUID id) implements Query<AuthorResponse> {

}
