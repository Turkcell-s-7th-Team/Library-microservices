package com.TurkcellTakim7.category_service.application.commands;

public record UpdateCategoryCommand(String id, String name, boolean active) {
}
