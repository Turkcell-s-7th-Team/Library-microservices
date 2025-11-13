package com.TurkcellTakim7.book_service.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.book_service.domain.entities.Book;
import com.TurkcellTakim7.book_service.domain.repositories.BookRepository;
import com.TurkcellTakim7.book_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.book_service.domain.valueobjects.CategoryId;
import com.TurkcellTakim7.book_service.domain.valueobjects.ISBN;
import com.TurkcellTakim7.book_service.domain.valueobjects.PublisherId;
import com.TurkcellTakim7.book_service.infrastructure.mapper.BookEntityMapper;
import com.TurkcellTakim7.book_service.infrastructure.repository.SpringDataBookRepository;

@Component
public class BookRepositoryAdapter implements BookRepository {

  private final SpringDataBookRepository springDataBookRepository;
  private final BookEntityMapper bookEntityMapper;

  public BookRepositoryAdapter(SpringDataBookRepository springDataBookRepository, BookEntityMapper bookEntityMapper) {
    this.springDataBookRepository = springDataBookRepository;
    this.bookEntityMapper = bookEntityMapper;
  }

  @Override
  public void deleteById(BookId bookId) {
    springDataBookRepository.deleteById(bookId.value());
  }

  @Override
  public Optional<Book> findByISBN(ISBN isbn) {
    return springDataBookRepository.findByIsbn(isbn.value())
        .map(bookEntityMapper::toDomain);
  }

  @Override
  public Optional<Book> findById(BookId bookId) {
    return springDataBookRepository.findById(bookId.value())
        .map(bookEntityMapper::toDomain);
  }

  @Override
  public List<Book> getAllBooks() {
    return springDataBookRepository.findAll().stream()
        .map(bookEntityMapper::toDomain).toList();
  }

  @Override
  public boolean isAvailable(BookId id) {
    return springDataBookRepository.existsByIdAndAvailableCopiesGreaterThan(id.value(), 0);
  }

  @Override
  public boolean isIsbnAlreadyExist(ISBN isbn) {
    return springDataBookRepository.existsByIsbn(isbn.value());
  }

  @Override
  public List<Book> listByCategory(CategoryId categoryId) {
    return springDataBookRepository.findAllByCategoryId(categoryId.value()).stream()
        .map(bookEntityMapper::toDomain)
        .toList();
  }

  @Override
  public List<Book> listByPublisher(PublisherId publisherId) {
    return springDataBookRepository.findAllByPublisherId(publisherId.value()).stream()
        .map(bookEntityMapper::toDomain)
        .toList();
  }

  @Override
  public Book save(Book book) {
    var entity = bookEntityMapper.toBookEntity(book);
    entity = springDataBookRepository.save(entity);
    return bookEntityMapper.toDomain(entity);
  }

}
