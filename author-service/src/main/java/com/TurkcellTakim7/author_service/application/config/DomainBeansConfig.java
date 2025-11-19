package com.TurkcellTakim7.author_service.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.TurkcellTakim7.author_service.domain.repositories.AuthorRepository;
import com.TurkcellTakim7.author_service.domain.services.AuthorDomainService;

@Configuration
public class DomainBeansConfig {
  @Bean
  public AuthorDomainService authorDomainService(AuthorRepository authorRepository) {
    return new AuthorDomainService(authorRepository);
  }

}
