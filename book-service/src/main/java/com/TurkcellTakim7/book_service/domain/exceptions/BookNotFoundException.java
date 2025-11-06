package com.TurkcellTakim7.book_service.domain.exceptions;

import com.TurkcellTakim7.book_service.domain.valueobjects.BookId;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(BookId id) {
    super("Book not found with given id: " + id);
  }

}
