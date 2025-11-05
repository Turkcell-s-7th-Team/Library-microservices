package com.TurkcellTakim7.loan_service.domain.valueobjects;

import java.io.Serializable;
import java.util.UUID;

public record MemberId(UUID value) implements Serializable {

    public MemberId {
        if (value == null) {
            throw new IllegalArgumentException("MemberId cannot be null");
        }
    }

    public static MemberId fromString(String raw) {
        return new MemberId(UUID.fromString(raw));
    }
}
