package com.TurkcellTakim7.book_service.domain.exceptions;

public class InvalidPublishYearException extends RuntimeException{
  public InvalidPublishYearException(){
      super("publishYear cannot be in the future");
  }

}
