package com.TurkcellTakim7.loan_service.infrastructure.adapter.feignClients.bookClient;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service") // application.yml'deki servis adı
public interface BookClient {

  @GetMapping("/api/v1/books/{bookId}/status")
  Boolean getBookValidationInfo(@PathVariable("bookId") UUID bookId);
}