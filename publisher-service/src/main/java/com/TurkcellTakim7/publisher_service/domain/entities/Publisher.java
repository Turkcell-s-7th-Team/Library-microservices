package com.TurkcellTakim7.publisher_service.domain.entities;

import java.util.Objects;

import com.TurkcellTakim7.publisher_service.domain.valueobjects.Address;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherId;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherName;

public class Publisher {
  private final PublisherId id;
  private PublisherName publisherName;
  private Address address;

  public Publisher(PublisherId id, PublisherName publisherName, Address address) {
    this.id = Objects.requireNonNull(id, "PublisherId cannot be null");
    this.publisherName = Objects.requireNonNull(publisherName, "Name cannot be null");
    this.address = Objects.requireNonNull(address, "Address cannot be null");
  }

  public PublisherId getId() {
    return id;
  }

  public PublisherName getName() {
    return publisherName;
  }

  public Address getAddress() {
    return address;
  }

  public static Publisher create(PublisherName PublisherName, Address address) {
    return new Publisher(PublisherId.generate(), PublisherName, address);
  }

  public static Publisher rehydrate(PublisherId publisherId, PublisherName publisherName, Address address) {
    return new Publisher(publisherId, publisherName, address);
  }

  public void updatePublisher(PublisherName name, Address address) {
    Objects.requireNonNull(publisherName);
    Objects.requireNonNull(address);
    this.address = address;
    this.publisherName = name;
  }
}
