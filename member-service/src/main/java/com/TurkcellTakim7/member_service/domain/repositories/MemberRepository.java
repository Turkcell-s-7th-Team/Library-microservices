package com.TurkcellTakim7.member_service.domain.repositories;

import com.TurkcellTakim7.member_service.domain.entities.Member;
import com.TurkcellTakim7.member_service.domain.valueobjects.Email;
import com.TurkcellTakim7.member_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.member_service.domain.valueobjects.MembershipLevel;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(MemberId memberId);

    Optional<Member> findByEmail(Email email);

    List<Member> getAllMembers();

    List<Member> findByMembershipLevel(MembershipLevel membershipLevel);

    List<Member> findByNameContaining(String name);

    void deleteById(MemberId memberId);

    boolean existsByEmail(Email email);
}
