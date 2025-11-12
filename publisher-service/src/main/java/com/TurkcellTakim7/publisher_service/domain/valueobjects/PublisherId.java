package com.TurkcellTakim7.publisher_service.domain.valueobjects;

import java.util.Objects;
import java.util.UUID;

public record PublisherId(UUID value) {
  public PublisherId {
    Objects.requireNonNull(value, "Value for PublisherId cannot be null");
  }

  public static PublisherId generate() {
    return new PublisherId(UUID.randomUUID());
  }
}
