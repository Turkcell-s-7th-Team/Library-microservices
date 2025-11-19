package com.TurkcellTakim7.reservation_service.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.TurkcellTakim7.reservation_service.domain.repository.ReservationRepository;
import com.TurkcellTakim7.reservation_service.domain.service.ReservationDomainService;

@Configuration
public class DomainBeansConfig {
  @Bean
  public ReservationDomainService reservationDomainService(ReservationRepository reservationRepository) {
    return new ReservationDomainService(reservationRepository);
  }
}
