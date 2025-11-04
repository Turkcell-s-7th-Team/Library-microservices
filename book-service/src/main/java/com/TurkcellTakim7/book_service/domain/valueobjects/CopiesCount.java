package com.TurkcellTakim7.book_service.domain.valueobjects;

public record CopiesCount(int value) {

  public CopiesCount {
    if (value < 0) {
      throw new IllegalArgumentException("Total copies cannot be negative!");
    }
  }

  public CopiesCount addCopies(int amount) {
    return new CopiesCount(value + amount);
  }

  public CopiesCount removeCopies(int amount) {
    return new CopiesCount(value - amount);
  }
}
