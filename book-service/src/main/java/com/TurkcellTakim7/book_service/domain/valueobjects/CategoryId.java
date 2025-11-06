package com.TurkcellTakim7.book_service.domain.valueobjects;

import java.util.Objects;
import java.util.UUID;

public record CategoryId(UUID value) {
  public CategoryId {
    Objects.requireNonNull(value, "CategoryId cannot be null!");
  }
}
