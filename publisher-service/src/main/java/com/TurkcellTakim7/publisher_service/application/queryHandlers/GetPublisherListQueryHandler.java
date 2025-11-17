package com.TurkcellTakim7.publisher_service.application.queryHandlers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.publisher_service.application.core.QueryHandler;
import com.TurkcellTakim7.publisher_service.application.dto.PublisherResponse;
import com.TurkcellTakim7.publisher_service.application.mapper.GetPublisherMapper;
import com.TurkcellTakim7.publisher_service.application.queries.GetPublisherListQuery;
import com.TurkcellTakim7.publisher_service.domain.entities.Publisher;
import com.TurkcellTakim7.publisher_service.domain.services.PublisherDomainService;

@Component
public class GetPublisherListQueryHandler implements QueryHandler<GetPublisherListQuery, List<PublisherResponse>> {

  private final PublisherDomainService publisherDomainService;
  private final GetPublisherMapper getPublisherMapper;

  public GetPublisherListQueryHandler(PublisherDomainService publisherDomainService,
      GetPublisherMapper getPublisherMapper) {
    this.publisherDomainService = publisherDomainService;
    this.getPublisherMapper = getPublisherMapper;
  }

  @Override
  public List<PublisherResponse> handle(GetPublisherListQuery query) {
    List<Publisher> publisherList = publisherDomainService.getPublisherList();
    return publisherList.stream()
        .map(getPublisherMapper::toResponse).toList();
  }
}
