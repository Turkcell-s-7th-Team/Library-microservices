package com.TurkcellTakim7.publisher_service.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.publisher_service.domain.entities.Publisher;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.Address;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherId;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherName;
import com.TurkcellTakim7.publisher_service.infrastructure.entities.JpaPublisherEntity;

@Component
public class PublisherEntityMapper {
  public JpaPublisherEntity toEntity(Publisher publisher) {
    JpaPublisherEntity entity = new JpaPublisherEntity();
    entity.setId(publisher.getId().value());
    entity.setName(publisher.getName().value());
    entity.setAddress(publisher.getAddress().value());

    return entity;
  }

  public Publisher toDomain(JpaPublisherEntity entity) {
    return Publisher.rehydrate(new PublisherId(entity.getId()), new PublisherName(entity.getName()),
        new Address(entity.getAddress()));
  }
}
