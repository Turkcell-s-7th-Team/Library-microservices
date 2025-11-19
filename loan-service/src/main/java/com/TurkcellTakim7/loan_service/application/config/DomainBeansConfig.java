package com.TurkcellTakim7.loan_service.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.TurkcellTakim7.loan_service.domain.repositories.LoanRepository;
import com.TurkcellTakim7.loan_service.domain.services.LoanDomainService;

@Configuration
public class DomainBeansConfig {
  @Bean
  public LoanDomainService loanDomainService(LoanRepository loanRepository) {
    return new LoanDomainService(loanRepository);
  }
}
