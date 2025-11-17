package com.TurkcellTakim7.publisher_service.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.TurkcellTakim7.publisher_service.domain.repositories.PublisherRepository;
import com.TurkcellTakim7.publisher_service.domain.services.PublisherDomainService;

@Configuration
public class DomainBeansConfig {

  @Bean
  public PublisherDomainService publisherDomainService(PublisherRepository publisherRepository) {
    return new PublisherDomainService(publisherRepository);
  }
}
