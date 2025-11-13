package com.TurkcellTakim7.book_service.application.queryHandler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.book_service.application.core.QueryHandler;
import com.TurkcellTakim7.book_service.application.dto.BookResponse;
import com.TurkcellTakim7.book_service.application.mapper.GetBookMapper;
import com.TurkcellTakim7.book_service.application.queries.GetBookListQuery;
import com.TurkcellTakim7.book_service.domain.entities.Book;
import com.TurkcellTakim7.book_service.domain.services.BookDomainService;

@Component
public class GetBookListQueryHandler implements QueryHandler<GetBookListQuery, List<BookResponse>> {

  private final BookDomainService bookDomainService;
  private final GetBookMapper getBookMapper;

  public GetBookListQueryHandler(BookDomainService bookDomainService, GetBookMapper getBookMapper) {
    this.bookDomainService = bookDomainService;
    this.getBookMapper = getBookMapper;
  }

  @Override
  public List<BookResponse> handle(GetBookListQuery query) {
    List<Book> bookList = bookDomainService.getBookList();
    return bookList.stream().map(getBookMapper::toResponse).toList();
  }
}
