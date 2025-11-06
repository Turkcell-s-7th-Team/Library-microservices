package com.TurkcellTakim7.book_service.domain.exceptions;

public class InvalidCopiesCountException extends RuntimeException {
  public InvalidCopiesCountException() {
    super("availableCopies cannot be more than copiesCount");
  }

}
