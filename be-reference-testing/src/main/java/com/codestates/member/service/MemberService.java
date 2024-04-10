package com.codestates.member.service;

import com.codestates.member.entity.Member;
import org.springframework.data.domain.Page;

public interface MemberService {
    Member createMember(Member member);
    Member updateMember(Member member);
    Member findMember(long memberId);
    Page<Member> findMembers(int page, int size);
    void deleteMember(long memberId);
    Member findVerifiedMember(long memberId);
}
