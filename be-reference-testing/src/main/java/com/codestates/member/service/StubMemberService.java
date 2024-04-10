package com.codestates.member.service;

import com.codestates.member.entity.Member;
import com.codestates.stamp.Stamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 *  Member Stub 데이터를 제공하는 StubMemberService 클래스
 *  - @PostConstruct를 이용해 의존성 주입(DI)에 대한 초기화 이후 수행될 작업을 추가한다.
 *      - 여기서는 Stub 데이터를 초기화하도록 함.
 *  - StubMemberService 클래스가 Bean으로 등록될 수 있도록 BeanConfig에서 Bean을 설정해준다.
 */
public class StubMemberService implements MemberService {
    private Member stubMember1;
    private Member stubMember2;

    @PostConstruct
    public void init() {
        stubMember1 = new Member();
        stubMember1.setMemberId(1L);
        stubMember1.setEmail("hgd1@gmail.com");
        stubMember1.setName("Hong Gil Dong1");
        stubMember1.setPhone("010-1111-1111");
        stubMember1.setStamp(new Stamp());

        stubMember2 = new Member();
        stubMember2.setMemberId(2L);
        stubMember2.setEmail("hgd@gmail.com");
        stubMember2.setName("Hong Gil Dong");
        stubMember2.setPhone("010-2222-2222");
        stubMember2.setStamp(new Stamp());
    }

    @Override
    public Member createMember(Member member) {
        return stubMember1;
    }

    @Override
    public Member updateMember(Member member) {
        return stubMember1;
    }

    @Override
    public Member findMember(long memberId) {
        return stubMember1;
    }

    @Override
    public Page<Member> findMembers(int page, int size) {
        return new PageImpl<>(List.of(stubMember1, stubMember2),
                PageRequest.of(0, 10, Sort.by("memberId").descending()),
                2);
    }

    @Override
    public void deleteMember(long memberId) {

    }

    @Override
    public Member findVerifiedMember(long memberId) {
        return null;
    }
}
