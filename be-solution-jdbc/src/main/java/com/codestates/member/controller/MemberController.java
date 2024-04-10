package com.codestates.member.controller;

import com.codestates.response.MultiResponseDto;
import com.codestates.response.SingleResponseDto;
import com.codestates.member.dto.MemberPatchDto;
import com.codestates.member.dto.MemberPostDto;
import com.codestates.member.entity.Member;
import com.codestates.member.mapper.MemberMapper;
import com.codestates.member.service.MemberService;
import com.codestates.utils.UriCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;


/**
 *  <h3>Spring Data JDBC를 이용한 데이터 액세스 실습 Solution 코드 포함</h3>
 *  MemberController의 {@link #getMembers(int, int)}에는 실습 과제의 핵심 기능인 페이지네이션 기능에 대한 Solution 코드가 포함되어 있습니다.
 */
@RestController
@RequestMapping("/v10/members")
@Validated
@Slf4j
public class MemberController {
    private final static String MEMBER_DEFAULT_URL = "/v10/members";
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberDto) {
        Member member =
                memberService.createMember(mapper.memberPostDtoToMember(memberDto));
        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, member.getMemberId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);

        Member member =
                memberService.updateMemberV2(mapper.memberPatchDtoToMember(memberPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponseDto(member)),
                HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id") @Positive long memberId) {
        Member member = memberService.findMember(memberId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponseDto(member))
                , HttpStatus.OK);
    }

    /**
     * 페이지네이션 처리가 된 회원 정보 목록을 response body로 전송하기 위한 Solution 코드입니다.
     * <p>
     *     <b>Solution 키 포인트</b>
     * </p>
     * <ul>
     *     <li>
     *         Spring Framework에서 지원하는 페이지네이션 기능을 이용하기 위해서는 기본적으로 클라이언트 측으로부터 page 정보와 size 정보를 전달 받아야합니다.
     *         page와 size는 request body처럼 핵심 정보가 아닌 부가 정보로 간주 되며 부가 정보의 경우, 주로 {@literal @}RequestParam 애너테이션을 이용한
     *         쿼리 파라미터(Query Parameter 또는 Query String)로 전달 받을 수 있습니다.
     *     </li>
     *     <li>
     *         Spring에서는 기본적으로 page 번호를 0부터 시작합니다.
     *         따라서 클라이언트 측에서는 human readable하게 page 번호를 1부터 사용할 수 있도록 하고,
     *         Controller 쪽에서 page - 1을 해서 올바른 페이지 번호에 해당하는 데이터를 조회하도록 해주는 것이 좋습니다.
     *     </li>
     *     <li>
     *         size는 한 페이지 당 제공하는 데이터(row)의 개 수를 의미합니다. 구글 같은 검색 화면에서 한 페이지당 보여지는 정보의 개 수를 생각하면 됩니다.
     *     </li>
     *     <li>
     *         {@link com.codestates.member.service.MemberService#findMembers(int, int)}에서 return 값으로 전달하는
     *         Page&lt;Member&gt;에는 페이지네이션 정보와 List&lt;Member&gt;가 함께 포함되어 있으므로,
     *         페이지네이션 정보와 List&lt;Member&gt;를 별도로 분리해서 List&lt;MemberResponseDto&gt;에 포함시켜야 합니다.
     *     </li>
     * </ul>
     * @see <a href="https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Page.html" target="_blank">Page</a>
     * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestParam.html" target="_blank">@RequestParam</a>
     *
     * @param page  페이지네이션을 위한 페이지 번호
     * @param size  한 페이지에 표시되어야 할 데이터의 개수
     * @return  클라이언트 측에 전송하는 List&lt;MemberResponseDto&gt;를 포함한 ResponseEntity
     */
    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<Member> pageMembers = memberService.findMembers(page - 1, size);
        List<Member> members = pageMembers.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.membersToMemberResponseDtos(members),
                        pageMembers),
                HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
