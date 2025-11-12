package com.TurkcellTakim7.publisher_service.domain.valueobjects;

import java.util.Objects;

public record PublisherName(String value) {
  public PublisherName {
    Objects.requireNonNull(value, "Value for PublisherName cannot be null.");
  }
}
