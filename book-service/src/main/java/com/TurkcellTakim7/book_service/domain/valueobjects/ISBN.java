package com.TurkcellTakim7.book_service.domain.valueobjects;

public record ISBN(String value) {

  public ISBN {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("ISBN cannot be empty!");
    }
    if (value.length() < 10 || value.length() > 20) {
      throw new IllegalArgumentException("ISBN length must be 10-20 characters!");
    }
  }
}