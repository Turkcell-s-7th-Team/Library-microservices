package com.TurkcellTakim7.author_service.application.dtos;

import java.util.UUID;

public record AuthorResponse(UUID id, String name, String surname, String email, String phoneNumber) {

}
