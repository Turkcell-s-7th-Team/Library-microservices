package com.TurkcellTakim7.category_service.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.TurkcellTakim7.category_service.domain.repositories.CategoryRepository;
import com.TurkcellTakim7.category_service.domain.services.CategoryDomainService;

@Configuration
public class DomainBeansConfig {

  @Bean
  public CategoryDomainService categoryDomainService(CategoryRepository categoryRepository) {
    return new CategoryDomainService(categoryRepository);
  }
}
