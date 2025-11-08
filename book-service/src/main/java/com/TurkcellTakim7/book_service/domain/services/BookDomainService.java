package com.TurkcellTakim7.book_service.domain.services;

import java.time.Year;
import java.util.List;

import com.TurkcellTakim7.book_service.domain.entities.Book;
import com.TurkcellTakim7.book_service.domain.exceptions.BookNotFoundException;
import com.TurkcellTakim7.book_service.domain.exceptions.InvalidCopiesCountException;
import com.TurkcellTakim7.book_service.domain.exceptions.InvalidPublishYearException;
import com.TurkcellTakim7.book_service.domain.exceptions.IsbnAlreadyExistException;
import com.TurkcellTakim7.book_service.domain.repositories.BookRepository;
import com.TurkcellTakim7.book_service.domain.valueobjects.AvailableCopies;
import com.TurkcellTakim7.book_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.book_service.domain.valueobjects.BookTitle;
import com.TurkcellTakim7.book_service.domain.valueobjects.CategoryId;
import com.TurkcellTakim7.book_service.domain.valueobjects.CopiesCount;
import com.TurkcellTakim7.book_service.domain.valueobjects.ISBN;
import com.TurkcellTakim7.book_service.domain.valueobjects.PublishYear;
import com.TurkcellTakim7.book_service.domain.valueobjects.PublisherId;

public class BookDomainService {

  private final BookRepository bookRepository;

  public BookDomainService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Book createBook(BookTitle bookTitle, CategoryId categoryId, AvailableCopies availableCopies,
      CopiesCount copiesCount, ISBN isbn, PublisherId publisherId, PublishYear publishYear) {

    if (availableCopies.value() > copiesCount.value()) {
      throw new InvalidCopiesCountException();
    }

    if (isIsbnAlreadyExist(isbn)) {
      throw new IsbnAlreadyExistException();
    }

    if (publishYear.year().isAfter(Year.now())) {
      throw new InvalidPublishYearException();
    }

    Book book = Book.create(bookTitle, categoryId, availableCopies, copiesCount, isbn, publisherId, publishYear);
    bookRepository.save(book);
    return book;
  }

  public Book getBook(BookId bookId) {
    Book existingBook = bookRepository.findById(bookId)
        .orElseThrow(() -> new BookNotFoundException(bookId));
    return existingBook;
  }

  public List<Book> getBookList() {
    return bookRepository.getAllBooks();
  }

  public Book updateBook(BookId bookId, BookTitle bookTitle, CategoryId categoryId, AvailableCopies availableCopies,
      CopiesCount copiesCount, PublisherId publisherId, PublishYear publishYear) {
    Book existingBook = bookRepository.findById(bookId).orElseThrow(
        () -> new BookNotFoundException(bookId));

    if (availableCopies.value() > copiesCount.value()) {
      throw new InvalidCopiesCountException();
    }

    if (publishYear.year().isAfter(Year.now())) {
      throw new InvalidPublishYearException();
    }

    existingBook.updateBook(bookTitle, categoryId, availableCopies, copiesCount, publisherId, publishYear);

    return existingBook;

  }

  public void deleteById(BookId id) {
    getBook(id);
    bookRepository.deleteById(id);
  }

  public Book addCopies(BookId id, int amount) {
    Book existingBook = getBook(id);
    existingBook.addCopies(amount);
    bookRepository.save(existingBook);
    return existingBook;
  }

  public Book removeCopies(BookId id, int amount) {
    Book existingBook = getBook(id);
    existingBook.removeCopies(amount);
    bookRepository.save(existingBook);
    return existingBook;
  }

  private boolean isIsbnAlreadyExist(ISBN isbn) {
    return bookRepository.isIsbnAlreadyExist(isbn);
  }

  public boolean isAvailable(BookId id) {
    Book existingBook = getBook(id);
    return existingBook.getAvailableCopies().value() > 0;
  }

  public List<Book> getBooksByCategory(CategoryId categoryId) {
    return bookRepository.listByCategory(categoryId);
  }

  public List<Book> getBooksByPublisher(PublisherId publisherId) {
    return bookRepository.listByPublisher(publisherId);
  }

  public Book borrowBook(BookId id) {
    Book existingBook = getBook(id);
    existingBook.borrow();
    bookRepository.save(existingBook);
    return existingBook;
  }

  public Book returnBook(BookId id) {
    Book existingBook = getBook(id);
    existingBook.returnBack();
    bookRepository.save(existingBook);
    return existingBook;
  }
}
