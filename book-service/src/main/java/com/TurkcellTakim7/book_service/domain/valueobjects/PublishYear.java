package com.TurkcellTakim7.book_service.domain.valueobjects;

import java.time.Year;
import java.util.Objects;


public record PublishYear(Year year) {

    public PublishYear {
        Objects.requireNonNull(year, "Publish year cannot be null.");
        Year currentYear = Year.now();
        
        if (year.isBefore(Year.of(1)) || year.isAfter(currentYear)) {
            throw new IllegalArgumentException("Invalid publish year! Year must be between 1 and " + currentYear.getValue());
        }
    }
    
    public static PublishYear of(int yearValue) {
        return new PublishYear(Year.of(yearValue));
    }
}