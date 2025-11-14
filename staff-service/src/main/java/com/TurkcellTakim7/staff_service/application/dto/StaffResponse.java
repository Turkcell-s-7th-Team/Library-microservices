package com.TurkcellTakim7.staff_service.application.dto;

import java.util.UUID;

public record StaffResponse(UUID id,String name,
String surname,
String staffPhone) {

}
