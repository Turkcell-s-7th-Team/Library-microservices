package com.TurkcellTakim7.member_service.domain.repositories;

import com.TurkcellTakim7.member_service.domain.entities.Member;
import com.TurkcellTakim7.member_service.domain.entities.MemberId;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(MemberId memberId);

    List<Member> getAllMembers();

    void deleteById(MemberId memberId);
}
