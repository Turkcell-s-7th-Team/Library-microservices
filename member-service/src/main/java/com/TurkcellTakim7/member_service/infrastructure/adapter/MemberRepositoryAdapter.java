package com.TurkcellTakim7.member_service.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.member_service.domain.entities.Member;
import com.TurkcellTakim7.member_service.domain.repositories.MemberRepository;
import com.TurkcellTakim7.member_service.domain.valueobjects.Email;
import com.TurkcellTakim7.member_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.member_service.domain.valueobjects.MembershipLevel;
import com.TurkcellTakim7.member_service.infrastructure.entities.JpaMemberEntity;
import com.TurkcellTakim7.member_service.infrastructure.mapper.MemberEntityMapper;
import com.TurkcellTakim7.member_service.infrastructure.repository.SpringDataMemberRepository;

@Component
public class MemberRepositoryAdapter implements MemberRepository {

  private final SpringDataMemberRepository springDataMemberRepository;
  private final MemberEntityMapper memberEntityMapper;

  public MemberRepositoryAdapter(SpringDataMemberRepository springDataMemberRepository,
      MemberEntityMapper memberEntityMapper) {
    this.springDataMemberRepository = springDataMemberRepository;
    this.memberEntityMapper = memberEntityMapper;
  }

  @Override
  public void deleteById(MemberId memberId) {
    springDataMemberRepository.deleteById(memberId.value());
  }

  @Override
  public boolean existsByEmail(Email email) {
    return springDataMemberRepository.existsByEmail(email.value());
  }

  @Override
  public Optional<Member> findByEmail(Email email) {
    return springDataMemberRepository.findByEmail(email.value())
        .map(memberEntityMapper::toDomain);
  }

  @Override
  public Optional<Member> findById(MemberId memberId) {

    return springDataMemberRepository.findById(memberId.value())
        .map(memberEntityMapper::toDomain);

  }

  @Override
  public List<Member> findByMembershipLevel(MembershipLevel membershipLevel) {
    List<JpaMemberEntity> entities = springDataMemberRepository.findByMembershipLevel(membershipLevel.value());
    return entities.stream()
        .map(memberEntityMapper::toDomain)
        .toList();
  }

  @Override
  public List<Member> findByNameContaining(String name) {
    List<JpaMemberEntity> entities = springDataMemberRepository.findByNameContaining(name);
    return entities.stream()
        .map(memberEntityMapper::toDomain)
        .toList();
  }

  @Override
  public List<Member> getAllMembers() {
    return springDataMemberRepository.findAll().stream().map(memberEntityMapper::toDomain).toList();
  }

  @Override
  public Member save(Member member) {

    JpaMemberEntity entity = memberEntityMapper.toEntity(member);
    entity = springDataMemberRepository.save(entity);
    return memberEntityMapper.toDomain(entity);
  }

}
