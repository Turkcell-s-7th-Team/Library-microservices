package com.TurkcellTakim7.author_service.application.dtos;

import java.util.UUID;

public record CreatedAuthorResponse(UUID id, String name, String surname) {

}
