package com.TurkcellTakim7.loan_service.infrastructure.messaging;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import com.TurkcellTakim7.loan_service.application.events.LoanCreatedEvent;

@Component
public class LoanEventPublisher {

  private final StreamBridge streamBridge;

  public LoanEventPublisher(StreamBridge streamBridge) {
    this.streamBridge = streamBridge;
  }

  public void publishLoanCreated(LoanCreatedEvent event) {
    streamBridge.send("loanCreated-out-0", event);
  }
}
