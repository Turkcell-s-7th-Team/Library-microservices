package com.TurkcellTakim7.book_service.application.queryHandler;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.book_service.application.core.QueryHandler;
import com.TurkcellTakim7.book_service.application.dto.BookResponse;
import com.TurkcellTakim7.book_service.application.mapper.GetBookMapper;
import com.TurkcellTakim7.book_service.application.queries.GetBookByIdQuery;
import com.TurkcellTakim7.book_service.domain.entities.Book;
import com.TurkcellTakim7.book_service.domain.services.BookDomainService;
import com.TurkcellTakim7.book_service.domain.valueobjects.BookId;

@Component
public class GetBookByIdQueryHandler implements QueryHandler<GetBookByIdQuery, BookResponse> {

  private final BookDomainService bookDomainService;
  private final GetBookMapper getBookMapper;

  public GetBookByIdQueryHandler(BookDomainService bookDomainService, GetBookMapper getBookMapper) {
    this.bookDomainService = bookDomainService;
    this.getBookMapper = getBookMapper;
  }

  @Override
  public BookResponse handle(GetBookByIdQuery query) {
    BookId bookId = new BookId(query.id());
    Book existingBook = bookDomainService.getBook(bookId);

    return getBookMapper.toResponse(existingBook);
  }

}
