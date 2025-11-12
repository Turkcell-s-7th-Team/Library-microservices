package com.TurkcellTakim7.staff_service.domain.valueobjects;

import java.util.Objects;
import java.util.regex.Pattern;

public record StaffPhone(String value) {
   
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{10,15}$");

    public StaffPhone {
        Objects.requireNonNull(value, "StaffPhone cannot be null");
       
        if (!PHONE_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid staff phone format: " + value);
        }
        
    }

    @Override
    public String toString() {
        return value;
    }
}
