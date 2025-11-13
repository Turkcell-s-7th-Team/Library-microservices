package com.TurkcellTakim7.book_service.domain.valueobjects;

public record BookTitle(String value) {

  public BookTitle {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("Book title cannot be empty!");
    }
    if (value.length() > 255) {
      throw new IllegalArgumentException("Book title max length is 255!");
    }
  }


}