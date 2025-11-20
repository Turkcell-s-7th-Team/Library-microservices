package com.TurkcellTakim7.fine_service.application.queries;

import java.util.List;

import com.TurkcellTakim7.fine_service.application.core.Query;
import com.TurkcellTakim7.fine_service.application.dto.FineResponse;

public record GetFineListQuery() implements Query<List<FineResponse>> {
}