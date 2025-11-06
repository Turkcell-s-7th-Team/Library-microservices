package com.TurkcellTakim7.member_service.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.TurkcellTakim7.member_service.domain.repositories.MemberRepository;
import com.TurkcellTakim7.member_service.domain.services.MemberDomainService;

@Configuration
public class DomainBeansConfig {

  @Bean
  public MemberDomainService memberDomainService(MemberRepository memberRepository) {
    return new MemberDomainService(memberRepository);
  }
}