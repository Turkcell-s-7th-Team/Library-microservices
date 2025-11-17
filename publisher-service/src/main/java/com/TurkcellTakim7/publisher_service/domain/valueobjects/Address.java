package com.TurkcellTakim7.publisher_service.domain.valueobjects;

import java.io.Serializable;
import java.util.Objects;

public record Address(String value) implements Serializable {
  public Address {
    Objects.requireNonNull(value, "Value for Address cannot be null.");
  }
}
