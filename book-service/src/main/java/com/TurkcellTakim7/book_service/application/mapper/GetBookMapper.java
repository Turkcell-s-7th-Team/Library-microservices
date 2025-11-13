package com.TurkcellTakim7.book_service.application.mapper;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.book_service.application.dto.BookResponse;
import com.TurkcellTakim7.book_service.domain.entities.Book;

@Component
public class GetBookMapper {

  public BookResponse toResponse(Book book) {

    return new BookResponse(book.getBookId().value(), book.getBookTitle().value(), book.getCategoryId().value(),
        book.getAvailableCopies().value(), book.getCopiesCount().value(), book.getIsbn()
            .value(),
        book.getPublisherId().value(), book.getPublishYear().year().getValue());
  }
}
