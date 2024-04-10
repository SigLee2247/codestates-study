package com.codestates.entity;

import com.codestates.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Pagination {

    private List<?> contentsPagination;

    private PageInfo pageInfo;
    public static PageInfo of(int page, int size, int totalElement, int totalPages) {
        return new PageInfo(page, size, totalElement, totalPages);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PageInfo {
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;

    }

}
