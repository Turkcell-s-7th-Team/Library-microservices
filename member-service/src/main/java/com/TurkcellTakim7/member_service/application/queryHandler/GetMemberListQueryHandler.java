package com.TurkcellTakim7.member_service.application.queryHandler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.member_service.application.core.QueryHandler;
import com.TurkcellTakim7.member_service.application.dto.MemberResponse;
import com.TurkcellTakim7.member_service.application.mapper.GetMemberMapper;
import com.TurkcellTakim7.member_service.application.queries.GetMemberListQuery;
import com.TurkcellTakim7.member_service.domain.entities.Member;
import com.TurkcellTakim7.member_service.domain.services.MemberDomainService;

@Component
public class GetMemberListQueryHandler implements QueryHandler<GetMemberListQuery, List<MemberResponse>> {

  private final MemberDomainService memberDomainService;
  private final GetMemberMapper getMemberMapper;

  public GetMemberListQueryHandler(MemberDomainService memberDomainService, GetMemberMapper getMemberMapper) {
    this.memberDomainService = memberDomainService;
    this.getMemberMapper = getMemberMapper;
  }

  @Override
  public List<MemberResponse> handle(GetMemberListQuery query) {
    List<Member> memberList = memberDomainService.getMemberList();

    return memberList.stream()
        .map(getMemberMapper::toResponse).toList();
  }

}
