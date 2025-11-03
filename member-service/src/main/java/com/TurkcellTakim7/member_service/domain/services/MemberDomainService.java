package com.TurkcellTakim7.member_service.domain.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.member_service.domain.entities.Member;
import com.TurkcellTakim7.member_service.domain.exceptions.EmailAlreadyExistsException;
import com.TurkcellTakim7.member_service.domain.exceptions.MemberNotFoundException;
import com.TurkcellTakim7.member_service.domain.repositories.MemberRepository;
import com.TurkcellTakim7.member_service.domain.valueobjects.Address;
import com.TurkcellTakim7.member_service.domain.valueobjects.Email;
import com.TurkcellTakim7.member_service.domain.valueobjects.MemberId;
import com.TurkcellTakim7.member_service.domain.valueobjects.MembershipLevel;
import com.TurkcellTakim7.member_service.domain.valueobjects.PhoneNumber;

@Component
public class MemberDomainService {

    private final MemberRepository memberRepository;

    public MemberDomainService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * Yeni bir üye oluşturur ve email benzersizliğini kontrol eder
     */
    public Member createMember(String name, String surname, Email email,
            String phoneNumber, String address,
            MembershipLevel membershipLevel) {

        // Email benzersizlik kontrolü
        if (isEmailAlreadyExists(email)) {
            throw new EmailAlreadyExistsException(email);
        }

        // Varsayılan üyelik tarihi bugün
        LocalDate membershipDate = LocalDate.now();

        // Varsayılan üyelik seviyesi STANDARD
        if (membershipLevel == null) {
            membershipLevel = MembershipLevel.STANDARD;
        }
        Member createdMember = new Member(
                MemberId.generate(),
                name,
                surname,
                email,
                new PhoneNumber(phoneNumber),
                new Address(address),
                membershipDate,
                membershipLevel);
        memberRepository.save(createdMember);
        return createdMember;
    }

    /**
     * Kayıtlı bir üyeyi getirir.
     */
    public Member getMember(MemberId memberId) {
        Member existingMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        return existingMember;
    }

    /**
     * Kayıtlı olan bütün üyeleri getirir.
     */
    public List<Member> getMemberList() {
        return memberRepository.getAllMembers();
    }

    /**
     * Üye bilgilerini günceller
     */
    public Member updateMember(MemberId memberId, String name, String surname,
            Email email, PhoneNumber phoneNumber, Address address) {

        Member existingMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        // Email değişiyorsa benzersizlik kontrolü
        if (!existingMember.getEmail().equals(email) && isEmailAlreadyExists(email)) {
            throw new EmailAlreadyExistsException(email);
        }

        // Üye bilgilerini güncelle
        existingMember.updatePersonalInfo(name, surname, email,
                phoneNumber, address);

        memberRepository.save(existingMember);
        return existingMember;
    }

    /**
     * Member Siler
     */

    public void deleteById(MemberId id) {
        getMember(id);
        memberRepository.deleteById(id);
    }

    /**
     * Üyelik seviyesini günceller
     */
    public Member updateMembershipLevel(MemberId memberId, MembershipLevel newLevel) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        member.updateMembershipLevel(newLevel);
        return member;
    }

    /**
     * Üyeyi banlar
     */
    public Member banMember(MemberId memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        member.ban();
        return member;
    }

    /**
     * Üyenin banını kaldırır
     */
    public Member unbanMember(MemberId memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        member.unban();
        return member;
    }

    /**
     * Email'in zaten var olup olmadığını kontrol eder
     */
    private boolean isEmailAlreadyExists(Email email) {
        return memberRepository.existsByEmail(email);
    }

    /**
     * Üyenin aktif olup olmadığını kontrol eder
     */
    public boolean isMemberActive(MemberId memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        return !member.getMembershipLevel().equals(MembershipLevel.BANNED);
    }

}
