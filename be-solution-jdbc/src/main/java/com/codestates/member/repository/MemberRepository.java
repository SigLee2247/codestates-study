package com.codestates.member.repository;

import com.codestates.member.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * <h3>Spring Data JDBC를 이용한 데이터 액세스 실습 Solution 코드 포함</h3>
 *
 * <p>
 *     <b>Solution 키 포인트</b>
 * </p>
 * <ul>
 *     <li>
 *         Spring Data JDBC의 Repository를 이용해 페이지네이션 처리된 데이터를 조회하기 위해서는
 *         페이지네이션 기능을 제공하는
 *         <a href="https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html" target="_blank">
 *              PagingAndSortingRepository
 *         </a>의
 *         <a href="https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html#findAll(org.springframework.data.domain.Pageable)" target="_blank">
 *              findAll(Pageable)
 *         </a>을 이용하면 됩니다.
 *     </li>
 * </ul>
 */
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
