package com.TurkcellTakim7.member_service.application.queries;

import java.util.List;

import com.TurkcellTakim7.member_service.application.core.Query;
import com.TurkcellTakim7.member_service.application.dto.MemberResponse;

public record GetMemberListQuery() implements Query<List<MemberResponse>> {

}
