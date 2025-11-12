package com.TurkcellTakim7.publisher_service.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.TurkcellTakim7.publisher_service.domain.entities.Publisher;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherId;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherName;

public interface PublisherRepository {

  Publisher save(Publisher publisher);

  Optional<Publisher> findById(PublisherId publisherId);

  List<Publisher> getAllPublishers();

  void deleteById(PublisherId publisherId);

  Optional<Publisher> findByName(PublisherName name);

  boolean existsByName(PublisherName name);

}
