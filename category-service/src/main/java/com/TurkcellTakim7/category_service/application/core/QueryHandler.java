package com.TurkcellTakim7.category_service.application.core;

public interface QueryHandler<Q, R> {
    R handle(Q query);
}