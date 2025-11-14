package com.TurkcellTakim7.publisher_service.application.queries;

import java.util.List;

import com.TurkcellTakim7.publisher_service.application.core.Query;
import com.TurkcellTakim7.publisher_service.application.dto.PublisherResponse;

public record GetPublisherListQuery() implements Query<List<PublisherResponse>> {

}
