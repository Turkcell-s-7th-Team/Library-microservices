package com.TurkcellTakim7.book_service.infrastructure.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.TurkcellTakim7.book_service.domain.exceptions.BookNotFoundException;
import com.TurkcellTakim7.book_service.domain.exceptions.InvalidCopiesCountException;
import com.TurkcellTakim7.book_service.domain.exceptions.InvalidPublishYearException;
import com.TurkcellTakim7.book_service.domain.exceptions.IsbnAlreadyExistException;
import com.TurkcellTakim7.book_service.domain.exceptions.NoAvailableCopiesException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BookNotFoundException.class)
  public ResponseEntity<Object> handleBookNotFound(BookNotFoundException ex) {
    return build(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(IsbnAlreadyExistException.class)
  public ResponseEntity<Object> handleIsbnAlreadyExist(IsbnAlreadyExistException ex) {
    return build(HttpStatus.CONFLICT, ex.getMessage());
  }

  @ExceptionHandler(NoAvailableCopiesException.class)
  public ResponseEntity<Object> handleNoAvailableCopies(NoAvailableCopiesException ex) {
    return build(HttpStatus.CONFLICT, ex.getMessage());
  }

  @ExceptionHandler({ InvalidCopiesCountException.class, InvalidPublishYearException.class,
      IllegalArgumentException.class })
  public ResponseEntity<Object> handleBadRequest(RuntimeException ex) {
    return build(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGeneric(Exception ex) {
    return build(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred");
  }

  private ResponseEntity<Object> build(HttpStatus status, String message) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", Instant.now().toString());
    body.put("status", status.value());
    body.put("error", status.getReasonPhrase());
    body.put("message", message);
    return ResponseEntity.status(status).body(body);
  }
}
