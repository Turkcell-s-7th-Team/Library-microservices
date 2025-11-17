package com.TurkcellTakim7.category_service.domain.valueobjects;

public final class CategoryName {
    private final String value;

    public CategoryName(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("Kategori adı boş olamaz");
        if (value.length() > 120)
            throw new IllegalArgumentException("Kategori adı 120 karakteri geçemez");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}
