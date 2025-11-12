package com.TurkcellTakim7.publisher_service.application.mapper;

import java.util.UUID;

import com.TurkcellTakim7.publisher_service.application.dto.UpdatedPublisherResponse;
import com.TurkcellTakim7.publisher_service.domain.entities.Publisher;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.Address;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherId;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherName;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdatePublisherMapper(
    @NotNull UUID publisherId,
    @NotBlank @Size(min = 2, max = 50) String publisherName,
    @NotBlank @Size(min = 2, max = 50) String address) {

  public Publisher toDomain(UpdatedPublisherResponse response) {
    return Publisher.rehydrate(new PublisherId(response.publisherId()), new PublisherName(response.publisherName()),
        new Address(response.address()));
  }

  public UpdatedPublisherResponse toResponse(Publisher publisher) {
    return new UpdatedPublisherResponse(toUuid(publisher),
        publisher.getName().toString(),
        publisher.getAddress().toString());
  }

  private UUID toUuid(Publisher publisher) {
    return publisher.getId() != null ? publisher.getId().value() : null;
  }

}
