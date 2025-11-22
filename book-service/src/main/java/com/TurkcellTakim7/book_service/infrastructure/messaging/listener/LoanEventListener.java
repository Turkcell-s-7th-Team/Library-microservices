package com.TurkcellTakim7.book_service.infrastructure.messaging.listener;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.TurkcellTakim7.book_service.domain.services.BookDomainService;
import com.TurkcellTakim7.book_service.domain.valueobjects.BookId;
import com.TurkcellTakim7.book_service.infrastructure.messaging.event.LoanCreatedEvent;
import com.TurkcellTakim7.book_service.infrastructure.messaging.event.LoanReturnedEvent;

@Component
public class LoanEventListener {

  private final BookDomainService bookDomainService;

  public LoanEventListener(BookDomainService bookDomainService) {
    this.bookDomainService = bookDomainService;
  }

  @Bean
  public Consumer<LoanCreatedEvent> loanCreated() {
    return event -> {
      System.out.println("📥 [BOOK] LoanCreatedEvent consumed, bookId = " + event.bookId());
      bookDomainService.borrowBook(new BookId(event.bookId()));
    };
  }

  @Bean
  public Consumer<LoanReturnedEvent> loanReturned() {
    return event -> {
      System.out.println("📥 [BOOK] LoanReturnedEvent consumed, bookId = " + event.bookId());
      bookDomainService.returnBook(new BookId(event.bookId()));
    };
  }
}
