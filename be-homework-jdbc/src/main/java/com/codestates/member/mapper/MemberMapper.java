package com.codestates.member.mapper;

import com.codestates.member.dto.MemberPatchDto;
import com.codestates.member.dto.MemberPostDto;
import com.codestates.member.dto.MemberResponseDto;
import com.codestates.member.dto.PaginationResponseDTO;
import com.codestates.member.entity.Member;
import com.codestates.entity.Pagination;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);

    default PaginationResponseDTO paginationToPaginationResponseDto(Pagination pagination) {
        if ( pagination == null ) {
            return null;
        }

        PaginationResponseDTO paginationResponseDTO = new PaginationResponseDTO();

        paginationResponseDTO.setPageInfo( pagination.getPageInfo() );
        paginationResponseDTO.setContentsPagination( membersToMemberResponseDtos((List<Member>) pagination.getContentsPagination()) );

        return paginationResponseDTO;
    }
}
