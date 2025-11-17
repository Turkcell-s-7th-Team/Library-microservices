package com.TurkcellTakim7.publisher_service.application.core;

public interface QueryHandler<Q extends Query<R>, R> {
  R handle(Q query);
}
