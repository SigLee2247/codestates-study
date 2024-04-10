package com.codestates.member.service;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import com.codestates.utils.CustomBeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *  <h3>Spring Data JDBC를 이용한 데이터 액세스 실습 Solution 코드 포함</h3>
 *
 *  MemberService의 {@link #findMembers(int, int)}에는 실습 과제의 핵심 기능인 페이지네이션 기능에 대한 Solution 코드가 포함되어 있습니다.
 */
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final CustomBeanUtils<Member> beanUtils;

    public MemberService(MemberRepository memberRepository, CustomBeanUtils<Member> beanUtils) {
        this.memberRepository = memberRepository;
        this.beanUtils = beanUtils;
    }

    public Member createMember(Member member) {
        // 이미 등록된 이메일인지 확인
        verifyExistsEmail(member.getEmail());

        return memberRepository.save(member);
    }

    public Member updateMemberV1(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));

        return memberRepository.save(findMember);
    }

    public Member updateMemberV2(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        Member updatingMember = beanUtils.copyNonNullProperties(member, findMember);
        return memberRepository.save(updatingMember);
    }

    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    /**
     * 페이지네이션 처리가 된 회원 정보 목록을 리턴하는 Solution 코드입니다.
     * <p>
     *     <b>Solution 키 포인트</b>
     * </p>
     * <ul>
     *     <li>
     *         Spring Data 패밀리 계열의 기술에서는
     *         <a href="https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Pageable.html" target="_blank">
     *              Pageable
     *         </a> 인터페이스의 구현 클래스인
     *         <a href="https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/PageRequest.html" target="_blank">
     *              PageRequest
     *         </a>를 이용해 손쉽게 페이지네이션 처리를 할 수 있습니다.
     *     </li>
     *     <li>
     *         <a href="https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/PageRequest.html" target="_blank">
     *              PageRequest
     *         </a>는
     *         <a href="https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Sort.html" target="_blank">
     *              Sort
     *         </a>를 이용해 정렬 기능을 적용할 수 있습니다.
     *     </li>
     *     <li>
     *         <a href="https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Sort.html" target="_blank">
     *              Sort
     *         </a>의 경우, 일반적으로 식별자 필드를 가장 많이 사용합니다.
     *     </li>
     * </ul>
     *
     * @see <a href="https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Pageable.html" target="_blank">Pageable</a>
     * @see <a href="https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/PageRequest.html" target="_blank">PageRequest</a>
     * @see <a href="https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Sort.html" target="_blank">Sort</a>
     *
     * @param page  페이지네이션을 위한 페이지 번호
     * @param size  한 페이지에 표시되어야 할 데이터의 개수
     * @return  페이지네이션 정보와 List&lt;Member&gt;가 포함된 Page&lt;Member&gt;
     */
    public Page<Member> findMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size, Sort.by("memberId").descending()));
    }

    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);

        memberRepository.delete(findMember);
    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
