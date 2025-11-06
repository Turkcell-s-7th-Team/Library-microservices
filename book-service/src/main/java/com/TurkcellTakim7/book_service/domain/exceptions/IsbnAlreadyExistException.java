package com.TurkcellTakim7.book_service.domain.exceptions;

public class IsbnAlreadyExistException extends RuntimeException {

  public IsbnAlreadyExistException() {
    super("Isbn already exist");
  }

}
