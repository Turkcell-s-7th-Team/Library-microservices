package com.TurkcellTakim7.book_service.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.TurkcellTakim7.book_service.domain.repositories.BookRepository;
import com.TurkcellTakim7.book_service.domain.services.BookDomainService;

@Configuration
public class DomainBeansConfig {
  @Bean
  public BookDomainService bookDomainService(BookRepository bookRepository) {
    return new BookDomainService(bookRepository);
  }
}
