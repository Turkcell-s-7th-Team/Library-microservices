package com.TurkcellTakim7.book_service.infrastructure.mapper;

import java.time.Year;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.book_service.domain.entities.Book;
import com.TurkcellTakim7.book_service.domain.valueobjects.AvailableCopies;
import com.TurkcellTakim7.book_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.book_service.domain.valueobjects.BookTitle;
import com.TurkcellTakim7.book_service.domain.valueobjects.CategoryId;
import com.TurkcellTakim7.book_service.domain.valueobjects.CopiesCount;
import com.TurkcellTakim7.book_service.domain.valueobjects.ISBN;
import com.TurkcellTakim7.book_service.domain.valueobjects.PublishYear;
import com.TurkcellTakim7.book_service.domain.valueobjects.PublisherId;
import com.TurkcellTakim7.book_service.infrastructure.entities.JpaBookEntity;

@Component
public class BookEntityMapper {

  public Book toDomain(JpaBookEntity entity) {
    return Book.rehydrate(new BookId(entity.getId()),
        new BookTitle(entity.getTitle()),
        new CategoryId(entity.getCategoryId()),
        new AvailableCopies(entity.getAvailableCopies()),
        new CopiesCount(entity.getCopiesCount()),
        new ISBN(entity.getIsbn()),
        new PublisherId(entity.getPublisherId()),
        new PublishYear(Year.of(entity.getPublishYear())));
  }

  public JpaBookEntity toBookEntity(Book book) {
    JpaBookEntity bookEntity = new JpaBookEntity();
    bookEntity.setId(book.getBookId().value());
    bookEntity.setTitle(book.getBookTitle().value());
    bookEntity.setCategoryId(book.getCategoryId().value());
    bookEntity.setAvailableCopies(book.getAvailableCopies().value());
    bookEntity.setCopiesCount(book.getCopiesCount().value());
    bookEntity.setIsbn(book.getIsbn().value());
    bookEntity.setPublisherId(book.getPublisherId().value());
    bookEntity.setPublishYear(book.getPublishYear().year().getValue());
    return bookEntity;
  }
}
