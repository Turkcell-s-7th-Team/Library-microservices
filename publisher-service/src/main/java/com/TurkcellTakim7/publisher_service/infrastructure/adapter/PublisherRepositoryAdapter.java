package com.TurkcellTakim7.publisher_service.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.publisher_service.domain.entities.Publisher;
import com.TurkcellTakim7.publisher_service.domain.repositories.PublisherRepository;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherId;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherName;
import com.TurkcellTakim7.publisher_service.infrastructure.entities.JpaPublisherEntity;
import com.TurkcellTakim7.publisher_service.infrastructure.mapper.PublisherEntityMapper;
import com.TurkcellTakim7.publisher_service.infrastructure.repository.SpringDataPublisherRepository;

@Component
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
  public boolean existsByPublisherName(PublisherName name) {
    return springDataPublisherRepository.existsByPublisherName(name.value());
  }

  @Override
  public Optional<Publisher> findById(PublisherId publisherId) {
    return springDataPublisherRepository.findById(publisherId.value()).map(publisherEntityMapper::toDomain);
  }

  @Override
  public Optional<Publisher> findByPublisherName(PublisherName name) {
    return springDataPublisherRepository.findByPublisherName(name.value());
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
