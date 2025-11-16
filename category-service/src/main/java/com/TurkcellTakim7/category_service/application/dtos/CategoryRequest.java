package com.TurkcellTakim7.category_service.application.dtos;

public class CategoryRequest {

    private String name;
    private boolean active;

    public CategoryRequest() {
    }

    public CategoryRequest(String name, boolean active) {
        this.name = name;
        this.active = active;
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
}
