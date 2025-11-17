package com.TurkcellTakim7.category_service.application.dtos;

import java.time.LocalDateTime;

public class CategoryResponse {
    private String id;
    private String name;
    private boolean active;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public CategoryResponse() {
    }

    public CategoryResponse(String id, String name, boolean active, LocalDateTime createdDate,
            LocalDateTime updatedDate) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
