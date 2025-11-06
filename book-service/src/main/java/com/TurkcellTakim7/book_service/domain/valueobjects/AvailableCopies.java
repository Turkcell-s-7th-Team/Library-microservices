package com.TurkcellTakim7.book_service.domain.valueobjects;

import com.TurkcellTakim7.book_service.domain.exceptions.NoAvailableCopiesException;

public record AvailableCopies(int value) {

    public AvailableCopies {
        if (value < 0) {
            throw new IllegalArgumentException("Available copies cannot be negative!");
        }
    }

    public AvailableCopies borrow() {
        return new AvailableCopies(value - 1);
    }

    public AvailableCopies returnBack() {
        if (value - 1 < 0) {
            throw new NoAvailableCopiesException();
        }
        return new AvailableCopies(value + 1);
    }

    public AvailableCopies increase(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount should be higher than 0.");
        }
        return new AvailableCopies(value + amount);
    }

    public AvailableCopies decrease(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount should be higher than 0.");
        }
        return new AvailableCopies(value - amount);
    }
}
