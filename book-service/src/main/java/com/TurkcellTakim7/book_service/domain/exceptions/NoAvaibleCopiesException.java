package com.TurkcellTakim7.book_service.domain.exceptions;

public class NoAvaibleCopiesException extends RuntimeException {

  public NoAvaibleCopiesException(){
    super("No avaible copies left for this book.");
  }
}
