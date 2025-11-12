package com.TurkcellTakim7.publisher_service.application.mapper;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.publisher_service.application.commands.CreatePublisherCommand;
import com.TurkcellTakim7.publisher_service.application.dto.CreatedPublisherResponse;
import com.TurkcellTakim7.publisher_service.domain.entities.Publisher;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.Address;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherName;

@Component
public class CreatePublisherMapper {
  public Publisher toDomain(CreatePublisherCommand command) {
    return Publisher.create(new PublisherName(command.publisherName()), new Address(command.address()));
  }

  public CreatedPublisherResponse toResponse(Publisher publisher) {
    return new CreatedPublisherResponse(publisher.getId().value(), publisher.getName().toString(),
        publisher.getAddress().toString());
  }
}
