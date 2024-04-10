package com.codestates.member.dto;

import com.codestates.entity.Pagination;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PaginationResponseDTO {
    private Pagination.PageInfo pageInfo;
    private List<?> contentsPagination;
}
