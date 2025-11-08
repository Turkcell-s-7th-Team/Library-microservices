package com.TurkcellTakim7.book_service.application.commandHandler;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.book_service.application.commands.DeleteBookCommand;
import com.TurkcellTakim7.book_service.application.core.CommandHandler;
import com.TurkcellTakim7.book_service.domain.services.BookDomainService;
import com.TurkcellTakim7.book_service.domain.valueobjects.BookId;

@Component
public class DeleteBookCommandHandler implements CommandHandler<DeleteBookCommand, Void> {

  private final BookDomainService bookDomainService;

  public DeleteBookCommandHandler(BookDomainService bookDomainService) {
    this.bookDomainService = bookDomainService;
  }

  @Override
  public Void handle(DeleteBookCommand command) {
    BookId bookId = new BookId(command.id());
    bookDomainService.deleteById(bookId);
    return null;
  }
}
