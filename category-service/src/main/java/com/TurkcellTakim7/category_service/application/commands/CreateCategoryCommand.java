package com.TurkcellTakim7.category_service.application.commands;

public class CreateCategoryCommand {
    private String name;

    public CreateCategoryCommand() {
    }

    public CreateCategoryCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
