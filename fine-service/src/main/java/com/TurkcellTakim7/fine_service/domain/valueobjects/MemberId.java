package com.TurkcellTakim7.fine_service.domain.valueobjects;

import java.util.UUID;

public record MemberId(UUID value) {
    public MemberId {
        if (value == null) {
            throw new IllegalArgumentException("MemberId cannot be null!");
        }
    }

    public static MemberId from(UUID id) {
        return new MemberId(id);
    }
}
