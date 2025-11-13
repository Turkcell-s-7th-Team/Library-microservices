package com.TurkcellTakim7.publisher_service.application.queryHandlers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.publisher_service.application.core.QueryHandler;
import com.TurkcellTakim7.publisher_service.application.dto.PublisherResponse;
import com.TurkcellTakim7.publisher_service.application.mapper.GetPublisherMapper;
import com.TurkcellTakim7.publisher_service.application.queries.GetPublisherByIdQuery;
import com.TurkcellTakim7.publisher_service.domain.services.PublisherDomainService;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherId;

@Component
public class GetPublisherByIdQueryHandler implements QueryHandler<GetPublisherByIdQuery, PublisherResponse> {

  private final GetPublisherMapper getPublisherMapper;
  private final PublisherDomainService publisherDomainService;

  public GetPublisherByIdQueryHandler(GetPublisherMapper getPublisherMapper,
      PublisherDomainService publisherDomainService) {
    this.getPublisherMapper = getPublisherMapper;
    this.publisherDomainService = publisherDomainService;
  }

  @Override
  public PublisherResponse handle(GetPublisherByIdQuery query) {
    PublisherId publisherId = new PublisherId(query.id());
    return getPublisherMapper.toResponse(publisherDomainService.getPublisher(publisherId));
  }

}
