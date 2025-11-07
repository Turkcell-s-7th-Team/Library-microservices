package com.TurkcellTakim7.book_service.domain.exceptions;

import com.TurkcellTakim7.book_service.domain.valueobjects.ISBN;

public class BookNotFoundWithGivenIsbn extends RuntimeException {

  public BookNotFoundWithGivenIsbn(ISBN isbn) {
    super("Book not found with given ISBN: " + isbn);
  }
}
