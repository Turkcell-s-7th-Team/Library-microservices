package com.TurkcellTakim7.staff_service.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.TurkcellTakim7.staff_service.domain.repositories.StaffRepository;
import com.TurkcellTakim7.staff_service.domain.services.StaffDomainService;

@Configuration
public class DomainBeansConfig {

    @Bean
    public StaffDomainService staffDomainService(StaffRepository staffRepository) {
        return new StaffDomainService(staffRepository);
    }
}
