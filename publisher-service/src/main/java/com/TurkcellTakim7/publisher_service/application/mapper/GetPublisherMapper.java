package com.TurkcellTakim7.publisher_service.application.mapper;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.publisher_service.application.dto.PublisherResponse;
import com.TurkcellTakim7.publisher_service.domain.entities.Publisher;

@Component
public class GetPublisherMapper {

  public PublisherResponse toResponse(Publisher publisher) {

    PublisherResponse response = new PublisherResponse(
        publisher.getId().value(),
        publisher.getPublisherName().value(),
        publisher.getAddress().value());
    return response;
  }
}
