package com.TurkcellTakim7.book_service.domain.entities;

import java.util.Objects;

import com.TurkcellTakim7.book_service.domain.valueobjects.AvailableCopies;
import com.TurkcellTakim7.book_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.book_service.domain.valueobjects.BookTitle;
import com.TurkcellTakim7.book_service.domain.valueobjects.CategoryId;
import com.TurkcellTakim7.book_service.domain.valueobjects.CopiesCount;
import com.TurkcellTakim7.book_service.domain.valueobjects.ISBN;
import com.TurkcellTakim7.book_service.domain.valueobjects.PublishYear;
import com.TurkcellTakim7.book_service.domain.valueobjects.PublisherId;

public class Book {

  private final BookId bookId;
  private BookTitle bookTitle;
  private CategoryId categoryId;
  private AvailableCopies availableCopies;
  private CopiesCount copiesCount;
  private final ISBN isbn;
  private final PublisherId publisherId;
  private final PublishYear publishYear;

  public Book(BookId bookId, BookTitle bookTitle, CategoryId categoryId, AvailableCopies availableCopies,
      CopiesCount copiesCount, ISBN isbn, PublisherId publisherId, PublishYear publishYear) {
    this.bookId = bookId;
    this.bookTitle = bookTitle;
    this.categoryId = categoryId;
    this.availableCopies = availableCopies;
    this.copiesCount = copiesCount;
    this.isbn = isbn;
    this.publisherId = publisherId;
    this.publishYear = publishYear;
  }

  public static Book create(BookTitle bookTitle, CategoryId categoryId, AvailableCopies availableCopies,
      CopiesCount copiesCount, ISBN isbn, PublisherId publisherId, PublishYear publishYear) {
    return new Book(
        BookId.generate(),
        Objects.requireNonNull(bookTitle),
        Objects.requireNonNull(categoryId),
        Objects.requireNonNull(availableCopies),
        Objects.requireNonNull(copiesCount),
        Objects.requireNonNull(isbn),
        Objects.requireNonNull(publisherId),
        Objects.requireNonNull(publishYear));
  };

  public static Book rehydrate(BookId bookId, BookTitle bookTitle, CategoryId categoryId,
      AvailableCopies availableCopies,
      CopiesCount copiesCount, ISBN isbn, PublisherId publisherId, PublishYear publishYear) {
    return new Book(bookId, bookTitle, categoryId, availableCopies, copiesCount, isbn, publisherId,
        publishYear);
  }

  public BookId getBookId() {
    return bookId;
  }

  public BookTitle getBookTitle() {
    return bookTitle;
  }

  public CategoryId getCategoryId() {
    return categoryId;
  }

  public AvailableCopies getAvailableCopies() {
    return availableCopies;
  }

  public CopiesCount getCopiesCount() {
    return copiesCount;
  }

  public ISBN getIsbn() {
    return isbn;
  }

  public PublisherId getPublisherId() {
    return publisherId;
  }

  public PublishYear getPublishYear() {
    return publishYear;
  };

  // Business Methods
  public void borrow() {
    this.availableCopies = this.availableCopies.decrease();
  }

  public void returnBack() {
    this.availableCopies = this.availableCopies.increase();
  }

  public void addCopies(int amount) {

    CopiesCount nextTotal = this.copiesCount.addCopies(amount);
    if (this.availableCopies.value() > nextTotal.value()) {
      throw new IllegalStateException("Available copies cannot exceed total copies");
    }
    this.copiesCount = nextTotal;
  }

  public void removeCopies(int amount) {
    CopiesCount nextTotal = this.copiesCount.removeCopies(amount);
    if (this.availableCopies.value() > nextTotal.value()) {
      throw new IllegalStateException("Cannot reduce total below available copies");
    }
    this.copiesCount = nextTotal;
  }

  public void updateTitle(BookTitle newTitle) {
    this.bookTitle = newTitle;
  }

  public void updateCategory(CategoryId newCategoryId) {
    Objects.requireNonNull(newCategoryId, "CategoryId cannot be null!");
    this.categoryId = newCategoryId;
  }
}
