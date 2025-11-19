package com.TurkcellTakim7.reservation_service.infrastructure.adapter.feignClients.bookClient;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service", path = "/api/v1/books")
public interface BookClient {

    @GetMapping("/{bookId}")
    BookResponse getBookById(@PathVariable("bookId") UUID bookId);

}
