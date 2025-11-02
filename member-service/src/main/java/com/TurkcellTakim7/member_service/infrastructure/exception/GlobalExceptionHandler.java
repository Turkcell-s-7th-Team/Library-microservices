package com.TurkcellTakim7.member_service.infrastructure.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.TurkcellTakim7.member_service.domain.exceptions.EmailAlreadyExistsException;
import com.TurkcellTakim7.member_service.domain.exceptions.InvalidMemberOperationException;
import com.TurkcellTakim7.member_service.domain.exceptions.MemberNotFoundException;
import com.TurkcellTakim7.member_service.domain.exceptions.MemberValidationException;

/**
 * Global exception handler - Tüm exception'ları merkezi bir şekilde yönetir
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MemberNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleMemberNotFoundException(MemberNotFoundException ex) {
    ErrorResponse error = new ErrorResponse(
        HttpStatus.NOT_FOUND.value(),
        ex.getMessage(),
        LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
    ErrorResponse error = new ErrorResponse(
        HttpStatus.CONFLICT.value(),
        ex.getMessage(),
        LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(MemberValidationException.class)
  public ResponseEntity<ErrorResponse> handleMemberValidationException(MemberValidationException ex) {
    ErrorResponse error = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        ex.getMessage(),
        LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidMemberOperationException.class)
  public ResponseEntity<ErrorResponse> handleInvalidMemberOperationException(InvalidMemberOperationException ex) {
    ErrorResponse error = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        ex.getMessage(),
        LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, Object> errors = new HashMap<>();
    Map<String, String> fieldErrors = new HashMap<>();

    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      fieldErrors.put(fieldName, errorMessage);
    });

    errors.put("status", HttpStatus.BAD_REQUEST.value());
    errors.put("message", "Validation failed");
    errors.put("errors", fieldErrors);
    errors.put("timestamp", LocalDateTime.now());

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
    ErrorResponse error = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        ex.getMessage(),
        LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
    ErrorResponse error = new ErrorResponse(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "An unexpected error occurred: " + ex.getMessage(),
        LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public record ErrorResponse(
      int status,
      String message,
      LocalDateTime timestamp) {
  }
}
