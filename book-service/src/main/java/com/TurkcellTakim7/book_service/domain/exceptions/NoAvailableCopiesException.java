package com.TurkcellTakim7.book_service.domain.exceptions;

public class NoAvailableCopiesException extends RuntimeException {

  public NoAvailableCopiesException(){
    super("No Available copies left for this book.");
  }
}
