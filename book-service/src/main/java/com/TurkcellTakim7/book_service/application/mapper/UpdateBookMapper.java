package com.TurkcellTakim7.book_service.application.mapper;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.book_service.application.dto.UpdatedBookResponse;
import com.TurkcellTakim7.book_service.domain.entities.Book;

@Component
public class UpdateBookMapper {

  public UpdatedBookResponse toResponse(Book book) {

    return new UpdatedBookResponse(book.getBookId().value(),
        book.getBookTitle().value(), book.getCategoryId().value(),
        book.getAvailableCopies().value(),
        book.getCopiesCount().value(),
        book.getIsbn().value(),
        book.getPublisherId().value(),
        book.getPublishYear().year().getValue());
  }
}
