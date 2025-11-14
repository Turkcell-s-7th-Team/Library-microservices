package com.TurkcellTakim7.publisher_service.domain.services;

import java.util.List;

import com.TurkcellTakim7.publisher_service.domain.entities.Publisher;
import com.TurkcellTakim7.publisher_service.domain.exceptions.PublisherNotFoundException;
import com.TurkcellTakim7.publisher_service.domain.exceptions.PublisherNotFoundWithGivenName;
import com.TurkcellTakim7.publisher_service.domain.exceptions.PublisherWithGivenNameIsAlreadyExistException;
import com.TurkcellTakim7.publisher_service.domain.repositories.PublisherRepository;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.Address;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherId;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherName;

public class PublisherDomainService {

  private final PublisherRepository publisherRepository;

  public PublisherDomainService(PublisherRepository publisherRepository) {
    this.publisherRepository = publisherRepository;
  }

  public Publisher createPublisher(PublisherName publisherName, Address address) {
    if (publisherRepository.existsByPublisherName(publisherName))
      throw new PublisherWithGivenNameIsAlreadyExistException();
    Publisher publisher = Publisher.create(publisherName, address);
    publisher = publisherRepository.save(publisher);
    return publisher;
  }

  public Publisher getPublisher(PublisherId publisherId) {
    Publisher existingPublisher = publisherRepository.findById(publisherId)
        .orElseThrow(() -> new PublisherNotFoundException(publisherId));
    return existingPublisher;
  }

  public List<Publisher> getPublisherList() {
    return publisherRepository.getAllPublishers();
  }

  public Publisher updatePublisher(PublisherId publisherId, PublisherName name, Address address) {
    Publisher existingPublisher = getPublisher(publisherId);
    existingPublisher.updatePublisher(name, address);
    publisherRepository.save(existingPublisher);
    return existingPublisher;
  }

  public void deletePublisher(PublisherId publisherId) {
    getPublisher(publisherId);
    publisherRepository.deleteById(publisherId);
  }

  public Publisher findByName(PublisherName publisherName) {
    Publisher existingPublisher = publisherRepository.findByPublisherName(publisherName)
        .orElseThrow(() -> new PublisherNotFoundWithGivenName(publisherName));
    return existingPublisher;
  }

  public Boolean existsByName(PublisherName name) {
    return publisherRepository.existsByPublisherName(name);
  }
}
