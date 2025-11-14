package com.TurkcellTakim7.staff_service.application.queries;

import java.util.List;

import com.TurkcellTakim7.staff_service.application.core.Query;
import com.TurkcellTakim7.staff_service.application.dto.StaffResponse;

public record GetStaffListQuery() implements Query<List<StaffResponse>> {
}
