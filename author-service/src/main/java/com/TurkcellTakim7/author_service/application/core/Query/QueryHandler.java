package com.TurkcellTakim7.author_service.application.core.Query;

public interface QueryHandler<Q extends Query<R>, R> {
    R handle(Q query);
  }
