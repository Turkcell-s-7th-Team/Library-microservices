package com.TurkcellTakim7.book_service.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.TurkcellTakim7.book_service.domain.entities.Book;
import com.TurkcellTakim7.book_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.book_service.domain.valueobjects.CategoryId;
import com.TurkcellTakim7.book_service.domain.valueobjects.ISBN;
import com.TurkcellTakim7.book_service.domain.valueobjects.PublisherId;

public interface BookRepository {
  Book save(Book book);

  Optional<Book> findById(BookId bookId);

  Optional<Book> findByISBN(ISBN isbn);

  boolean isIsbnAlreadyExist(ISBN isbn);

  List<Book> getAllBooks();

  void deleteById(BookId bookId);

  boolean isAvailable(BookId id);

List<Book> listByCategory(CategoryId categoryId);

List<Book> listByPublisher(PublisherId publisherId);

}
