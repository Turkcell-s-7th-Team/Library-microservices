package com.TurkcellTakim7.book_service.application.commandHandler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.TurkcellTakim7.book_service.application.commands.UpdateBookCommand;
import com.TurkcellTakim7.book_service.application.core.CommandHandler;
import com.TurkcellTakim7.book_service.application.dto.UpdatedBookResponse;
import com.TurkcellTakim7.book_service.application.mapper.UpdateBookMapper;
import com.TurkcellTakim7.book_service.domain.entities.Book;
import com.TurkcellTakim7.book_service.domain.repositories.BookRepository;
import com.TurkcellTakim7.book_service.domain.services.BookDomainService;
import com.TurkcellTakim7.book_service.domain.valueobjects.AvailableCopies;
import com.TurkcellTakim7.book_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.book_service.domain.valueobjects.BookTitle;
import com.TurkcellTakim7.book_service.domain.valueobjects.CategoryId;
import com.TurkcellTakim7.book_service.domain.valueobjects.CopiesCount;
import com.TurkcellTakim7.book_service.domain.valueobjects.PublishYear;
import com.TurkcellTakim7.book_service.domain.valueobjects.PublisherId;

@Component
public class UpdateBookCommandHandler implements CommandHandler<UpdateBookCommand, UpdatedBookResponse> {

  private final BookDomainService bookDomainService;
  private final BookRepository bookRepository;
  private final UpdateBookMapper updateBookMapper;

  public UpdateBookCommandHandler(BookDomainService bookDomainService, BookRepository bookRepository,
      UpdateBookMapper updateBookMapper) {
    this.bookDomainService = bookDomainService;
    this.bookRepository = bookRepository;
    this.updateBookMapper = updateBookMapper;
  }

  @Override
  @Transactional
  public UpdatedBookResponse handle(UpdateBookCommand command) {
    BookId bookId = new BookId(command.bookId());

    Book updatedBook = bookDomainService.updateBook(
        bookId,
        new BookTitle(command.title()),
        new CategoryId(command.categoryId()),
        new AvailableCopies(command.availableCopies()),
        new CopiesCount(command.copiesCount()),
        new PublisherId(command.publisherId()),
        PublishYear.of(command.publishYear()));

    updatedBook = bookRepository.save(updatedBook);

    return updateBookMapper.toResponse(updatedBook);
  }
}
