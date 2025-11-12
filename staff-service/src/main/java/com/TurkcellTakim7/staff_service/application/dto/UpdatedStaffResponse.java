package com.TurkcellTakim7.staff_service.application.dto;

import java.util.UUID;

public record UpdatedStaffResponse(UUID id,
String name,
String surname,
String staffPhone) {

}
