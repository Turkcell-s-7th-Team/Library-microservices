package com.TurkcellTakim7.staff_service.application.core;



public interface QueryHandler<Q extends Query<R>, R> {
    R handle(Q query);
}
