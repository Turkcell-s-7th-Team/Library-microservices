package com.TurkcellTakim7.category_service.domain.valueobjects;

public final class CategoryId {
    private final Long value;

    public CategoryId(Long value) {
        if (value == null || value <= 0)
            throw new IllegalArgumentException("Geçersiz kategori id");
        this.value = value;
    }

    public Long value() {
        return value;
    }
}
