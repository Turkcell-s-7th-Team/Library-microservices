package com.TurkcellTakim7.loan_service.application.exception;

public class MemberIsBannedException extends RuntimeException {

  public MemberIsBannedException() {
    super("Member is banned!");
  }

}
