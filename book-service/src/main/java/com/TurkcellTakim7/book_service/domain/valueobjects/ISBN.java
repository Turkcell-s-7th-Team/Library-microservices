package com.TurkcellTakim7.book_service.domain.valueobjects;

public record ISBN(String code) {

  public ISBN {
    if (code == null || code.isBlank()) {
      throw new IllegalArgumentException("ISBN cannot be empty!");
    }
    if (code.length() < 10 || code.length() > 20) {
      throw new IllegalArgumentException("ISBN length must be 10-20 characters!");
    }
  }
}