package com.TurkcellTakim7.member_service.messaging.listener;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.TurkcellTakim7.member_service.domain.entities.Member;
import com.TurkcellTakim7.member_service.domain.repositories.MemberRepository;
import com.TurkcellTakim7.member_service.domain.services.MemberDomainService;
import com.TurkcellTakim7.member_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.member_service.messaging.events.FineCreatedEvent;

@Component
public class FineEventListener {

  private final MemberDomainService memberDomainService;
  private final MemberRepository memberRepository;

  public FineEventListener(MemberDomainService memberDomainService, MemberRepository memberRepository) {
    this.memberDomainService = memberDomainService;
    this.memberRepository = memberRepository;
  }

  @Bean
  public Consumer<FineCreatedEvent> fineCreated() {
    return event -> {
      System.out.println("event alındı mı ?");
      MemberId memberId = new MemberId(event.memberId());

      Member updated = memberDomainService.banMember(memberId);

      memberRepository.save(updated);

      System.out.println("updatessssss: " + updated.getMembershipLevel());
    };
  }
}
