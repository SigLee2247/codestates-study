package com.codestates.member.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Member {
    @Id
    private Long memberId;
}
