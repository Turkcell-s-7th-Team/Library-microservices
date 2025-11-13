package com.TurkcellTakim7.publisher_service.domain.exceptions;

import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherId;

public class PublisherNotFoundException extends RuntimeException {
  public PublisherNotFoundException(PublisherId id){
    super("Publisher not found with given id: " + id);
  }
}
