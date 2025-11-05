package com.TurkcellTakim7.book_service.domain.valueobjects;

import com.TurkcellTakim7.book_service.domain.exceptions.NoAvailableCopiesException;

public record AvailableCopies(int value) {

    public AvailableCopies {
        if (value < 0) {
            throw new IllegalArgumentException("Available copies cannot be negative!");
        }
    }

    public AvailableCopies increase() {
        return new AvailableCopies(value + 1);
    }

    public AvailableCopies decrease() {
        if (value - 1 < 0) {
            throw new NoAvailableCopiesException();
        }
        return new AvailableCopies(value - 1);
    }
}
