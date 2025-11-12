package com.TurkcellTakim7.publisher_service.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import com.TurkcellTakim7.publisher_service.domain.entities.Publisher;
import com.TurkcellTakim7.publisher_service.domain.repositories.PublisherRepository;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherId;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherName;
import com.TurkcellTakim7.publisher_service.infrastructure.entities.JpaPublisherEntity;
import com.TurkcellTakim7.publisher_service.infrastructure.mapper.PublisherEntityMapper;
import com.TurkcellTakim7.publisher_service.infrastructure.repository.SpringDataPublisherRepository;

public class PublisherRepositoryAdapter implements PublisherRepository {

  private final PublisherEntityMapper publisherEntityMapper;
  private final SpringDataPublisherRepository springDataPublisherRepository;

  public PublisherRepositoryAdapter(PublisherEntityMapper publisherEntityMapper,
      com.TurkcellTakim7.publisher_service.infrastructure.repository.SpringDataPublisherRepository springDataPublisherRepository) {
    this.publisherEntityMapper = publisherEntityMapper;
    this.springDataPublisherRepository = springDataPublisherRepository;
  }

  @Override
  public void deleteById(PublisherId publisherId) {
    springDataPublisherRepository.deleteById(publisherId.value());
  }

  @Override
  public boolean existsByName(PublisherName name) {
    return springDataPublisherRepository.existsByName(name);
  }

  @Override
  public Optional<Publisher> findById(PublisherId publisherId) {
    return springDataPublisherRepository.findById(publisherId.value()).map(publisherEntityMapper::toDomain);
  }

  @Override
  public Optional<Publisher> findByName(PublisherName name) {
    return springDataPublisherRepository.findByName(name);
  }

  @Override
  public List<Publisher> getAllPublishers() {
    return springDataPublisherRepository.findAll()
        .stream()
        .map(publisherEntityMapper::toDomain).toList();
  }

  @Override
  public Publisher save(Publisher publisher) {
    JpaPublisherEntity entity = publisherEntityMapper.toEntity(publisher);
    entity = springDataPublisherRepository.save(entity);
    return publisherEntityMapper.toDomain(entity);
  }
}
