package com.TurkcellTakim7.publisher_service.application.mapper;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.publisher_service.application.dto.CreatedPublisherResponse;
import com.TurkcellTakim7.publisher_service.domain.entities.Publisher;

@Component
public class CreatePublisherMapper {
  public CreatedPublisherResponse toResponse(Publisher publisher) {
    return new CreatedPublisherResponse(
        publisher.getId().value(),
        publisher.getPublisherName().value(),
        publisher.getAddress().value());
  }
}
