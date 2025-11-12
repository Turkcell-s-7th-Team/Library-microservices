package com.TurkcellTakim7.publisher_service.domain.exceptions;

import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherName;

public class PublisherNotFoundWithGivenName extends RuntimeException {

  public PublisherNotFoundWithGivenName(PublisherName publisherName) {
    super("Publisher is not found with given name : " + publisherName);
  }
}
