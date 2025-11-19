package com.TurkcellTakim7.book_service.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkcellTakim7.book_service.application.commandHandler.CreateBookCommandHandler;
import com.TurkcellTakim7.book_service.application.commandHandler.DeleteBookCommandHandler;
import com.TurkcellTakim7.book_service.application.commandHandler.UpdateBookCommandHandler;
import com.TurkcellTakim7.book_service.application.commands.CreateBookCommand;
import com.TurkcellTakim7.book_service.application.commands.DeleteBookCommand;
import com.TurkcellTakim7.book_service.application.commands.UpdateBookCommand;
import com.TurkcellTakim7.book_service.application.dto.BookResponse;
import com.TurkcellTakim7.book_service.application.dto.CreatedBookResponse;
import com.TurkcellTakim7.book_service.application.dto.UpdateBookRequest;
import com.TurkcellTakim7.book_service.application.dto.UpdatedBookResponse;
import com.TurkcellTakim7.book_service.application.queries.GetBookByIdQuery;
import com.TurkcellTakim7.book_service.application.queries.GetBookListQuery;
import com.TurkcellTakim7.book_service.application.queryHandler.GetBookByIdQueryHandler;
import com.TurkcellTakim7.book_service.application.queryHandler.GetBookListQueryHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

  private final GetBookByIdQueryHandler getBookByIdQueryHandler;
  private final GetBookListQueryHandler getBookListQueryHandler;
  private final CreateBookCommandHandler createBookCommandHandler;
  private final DeleteBookCommandHandler deleteBookCommandHandler;
  private final UpdateBookCommandHandler updateBookCommandHandler;

  public BookController(GetBookByIdQueryHandler getBookByIdQueryHandler,
      GetBookListQueryHandler getBookListQueryHandler, CreateBookCommandHandler createBookCommandHandler,
      DeleteBookCommandHandler deleteBookCommandHandler, UpdateBookCommandHandler updateBookCommandHandler) {
    this.getBookByIdQueryHandler = getBookByIdQueryHandler;
    this.getBookListQueryHandler = getBookListQueryHandler;
    this.createBookCommandHandler = createBookCommandHandler;
    this.deleteBookCommandHandler = deleteBookCommandHandler;
    this.updateBookCommandHandler = updateBookCommandHandler;
  }

  @GetMapping("/{id}")
  public BookResponse getBook(@PathVariable UUID id) {
    return getBookByIdQueryHandler.handle(new GetBookByIdQuery(id));
  }

  @GetMapping("/{id}/status")
  public boolean  getBookStatus(@PathVariable UUID id) {
    
    BookResponse response = getBookByIdQueryHandler.handle(new GetBookByIdQuery(id));
    if(response.availableCopies() > 0){
      return true;
    }
    return false;
  }


  @GetMapping
  public List<BookResponse> getBookList() {
    return getBookListQueryHandler.handle(new GetBookListQuery());
  }

  @PostMapping
  public CreatedBookResponse createBook(@RequestBody CreateBookCommand command) {
    return createBookCommandHandler.handle(command);
  }

  @PutMapping("/{id}")
  public UpdatedBookResponse updateBook(
      @PathVariable UUID id,
      @RequestBody @Valid UpdateBookRequest request) {

    UpdateBookCommand command = new UpdateBookCommand(
        id,
        request.title(),
        request.availableCopies(),
        request.copiesCount(),
        request.categoryId(),
        request.publisherId(),
        request.publishYear());

    return updateBookCommandHandler.handle(command);
  }

  @DeleteMapping("/{id}")
  public Void deleteBook(@PathVariable UUID id) {
    return deleteBookCommandHandler.handle(new DeleteBookCommand(id));
  }
}
