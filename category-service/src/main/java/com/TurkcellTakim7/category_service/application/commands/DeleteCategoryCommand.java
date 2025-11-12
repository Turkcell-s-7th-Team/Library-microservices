package com.TurkcellTakim7.category_service.application.commands;

public class DeleteCategoryCommand {
    private String id;

    public DeleteCategoryCommand() {
    }

    public DeleteCategoryCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
