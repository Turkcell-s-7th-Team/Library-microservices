package com.TurkcellTakim7.member_service.application.queries;

import java.util.UUID;

import com.TurkcellTakim7.member_service.application.core.Query;
import com.TurkcellTakim7.member_service.application.dto.MemberResponse;

public record GetMemberQuery(UUID id) implements Query<MemberResponse> {
}
