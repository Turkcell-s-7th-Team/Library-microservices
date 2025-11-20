package com.TurkcellTakim7.loan_service.application.exception;

public class NoAvailableCopyException extends RuntimeException {
  public NoAvailableCopyException() {
    super("No available copy!");
  }
}
