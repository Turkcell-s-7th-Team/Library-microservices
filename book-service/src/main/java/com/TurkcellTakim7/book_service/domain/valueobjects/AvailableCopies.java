package com.TurkcellTakim7.book_service.domain.valueobjects;

public record AvailableCopies(int value) {

    public AvailableCopies {
        if (value < 0) {
            throw new IllegalArgumentException("Available copies cannot be negative!");
        }
    }

    public AvailableCopies increase(int maxTotal) {
        if (value + 1 > maxTotal) {
            throw new IllegalStateException("Available copies cannot exceed total copies!");
        }
        return new AvailableCopies(value + 1);
    }

    public AvailableCopies decrease() {
        if (value == 0) {
            throw new IllegalStateException("There are no available copies to borrow!");
        }
        return new AvailableCopies(value - 1);
    }
}
