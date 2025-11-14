package com.TurkcellTakim7.publisher_service.application.mapper;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.publisher_service.application.dto.PublisherResponse;
import com.TurkcellTakim7.publisher_service.domain.entities.Publisher;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.Address;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherId;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherName;

@Component
public class GetPublisherMapper {

  public PublisherResponse toResponse(Publisher publisher) {

    PublisherResponse response = new PublisherResponse(
        publisher.getId().value(),
        publisher.getPublisherName().value(),
        publisher.getAddress().value());
    return response;
  }

  public Publisher toDomain(PublisherResponse response) {
    return Publisher.rehydrate(new PublisherId(response.id()), new PublisherName(response.name()),
        new Address(response.address()));
  }
}
