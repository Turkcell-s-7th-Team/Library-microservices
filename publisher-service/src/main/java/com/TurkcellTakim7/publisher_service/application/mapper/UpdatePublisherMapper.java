package com.TurkcellTakim7.publisher_service.application.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.publisher_service.application.dto.UpdatedPublisherResponse;
import com.TurkcellTakim7.publisher_service.domain.entities.Publisher;

@Component
public class UpdatePublisherMapper {

  public UpdatedPublisherResponse toResponse(Publisher publisher) {
    return new UpdatedPublisherResponse(
        toUuid(publisher),
        publisher.getPublisherName().value(),
        publisher.getAddress().value());
  }

  private UUID toUuid(Publisher publisher) {
    return publisher.getId() != null ? publisher.getId().value() : null;
  }
}
