package com.TurkcellTakim7.loan_service.application.core.query;

public interface QueryHandler<Q extends Query<R>, R> {
    R handle(Q query);
}
