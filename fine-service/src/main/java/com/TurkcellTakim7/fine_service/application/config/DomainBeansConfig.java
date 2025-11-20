package com.TurkcellTakim7.fine_service.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.TurkcellTakim7.fine_service.domain.repositories.FineRepository;
import com.TurkcellTakim7.fine_service.domain.services.FineDomainService;

@Configuration
public class DomainBeansConfig {

    @Bean
    public FineDomainService fineDomainService(FineRepository fineRepository) {
        return new FineDomainService(fineRepository);
    }
}

