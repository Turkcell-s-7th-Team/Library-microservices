package com.TurkcellTakim7.staff_service.application.queries;

import java.util.UUID;

import com.TurkcellTakim7.staff_service.application.core.Query;
import com.TurkcellTakim7.staff_service.application.dto.StaffResponse;

public record GetStaffQuery(UUID id) implements Query<StaffResponse> {
}
