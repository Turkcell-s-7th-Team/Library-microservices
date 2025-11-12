package com.TurkcellTakim7.category_service.application.commands;

public class UpdateCategoryCommand {
    private String id;
    private String name;
    private boolean active;

    public UpdateCategoryCommand() {
    }

    public UpdateCategoryCommand(String id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
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
}
