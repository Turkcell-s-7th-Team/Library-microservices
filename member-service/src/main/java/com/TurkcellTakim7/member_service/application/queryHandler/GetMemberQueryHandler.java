package com.TurkcellTakim7.member_service.application.queryHandler;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.member_service.application.core.QueryHandler;
import com.TurkcellTakim7.member_service.application.dto.MemberResponse;
import com.TurkcellTakim7.member_service.application.mapper.GetMemberMapper;
import com.TurkcellTakim7.member_service.application.queries.GetMemberQuery;
import com.TurkcellTakim7.member_service.domain.entities.Member;
import com.TurkcellTakim7.member_service.domain.services.MemberDomainService;
import com.TurkcellTakim7.member_service.domain.valueobjects.MemberId;

@Component
public class GetMemberQueryHandler implements QueryHandler<GetMemberQuery, MemberResponse> {

  private final GetMemberMapper getMemberMapper;
  private final MemberDomainService memberDomainService;

  public GetMemberQueryHandler(GetMemberMapper getMemberMapper, MemberDomainService memberDomainService) {
    this.getMemberMapper = getMemberMapper;
    this.memberDomainService = memberDomainService;
  }

  public MemberResponse handle(GetMemberQuery query) {
    Member member = memberDomainService.getMember(new MemberId(query.id()));
    return getMemberMapper.toResponse(member);
  }
}
