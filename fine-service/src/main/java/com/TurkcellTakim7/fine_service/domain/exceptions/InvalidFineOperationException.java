package com.TurkcellTakim7.fine_service.domain.exceptions;

import com.TurkcellTakim7.fine_service.domain.valueobjects.FineId;

public class InvalidFineOperationException extends RuntimeException {

    private final FineId fineId;

    public InvalidFineOperationException(String message, FineId fineId) {
        super(message);
        this.fineId = fineId;
    }

    public InvalidFineOperationException(String message, FineId fineId, Throwable cause) {
        super(message, cause);
        this.fineId = fineId;
    }

    public FineId getFineId() {
        return fineId;
    }
}
