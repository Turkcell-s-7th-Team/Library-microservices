package com.TurkcellTakim7.book_service.application.commandHandler;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.book_service.application.commands.CreateBookCommand;
import com.TurkcellTakim7.book_service.application.core.CommandHandler;
import com.TurkcellTakim7.book_service.application.dto.CreatedBookResponse;
import com.TurkcellTakim7.book_service.application.mapper.CreateBookMapper;
import com.TurkcellTakim7.book_service.domain.entities.Book;
import com.TurkcellTakim7.book_service.domain.repositories.BookRepository;
import com.TurkcellTakim7.book_service.domain.services.BookDomainService;
import com.TurkcellTakim7.book_service.domain.valueobjects.AvailableCopies;
import com.TurkcellTakim7.book_service.domain.valueobjects.BookTitle;
import com.TurkcellTakim7.book_service.domain.valueobjects.CategoryId;
import com.TurkcellTakim7.book_service.domain.valueobjects.CopiesCount;
import com.TurkcellTakim7.book_service.domain.valueobjects.ISBN;
import com.TurkcellTakim7.book_service.domain.valueobjects.PublishYear;
import com.TurkcellTakim7.book_service.domain.valueobjects.PublisherId;

@Component
public class CreateBookCommandHandler implements CommandHandler<CreateBookCommand, CreatedBookResponse> {

  private final CreateBookMapper createBookMapper;
  private final BookDomainService bookDomainService;
  private final BookRepository bookRepository;

  public CreateBookCommandHandler(CreateBookMapper createBookMapper, BookDomainService bookDomainService,
      BookRepository bookRepository) {
    this.createBookMapper = createBookMapper;
    this.bookDomainService = bookDomainService;
    this.bookRepository = bookRepository;
  }

  @Override
  public CreatedBookResponse handle(CreateBookCommand command) {
    Book book = bookDomainService.createBook(
        new BookTitle(command.title()),
        new CategoryId(command.categoryId()),
        new AvailableCopies(command.availableCopies()),
        new CopiesCount(command.copiesCount()),
        new ISBN(command.isbn()),
        new PublisherId(command.publisherId()),
        PublishYear.of(command.publishYear()));
    book = bookRepository.save(book);
    return createBookMapper.toResponse(book);
  }
}
