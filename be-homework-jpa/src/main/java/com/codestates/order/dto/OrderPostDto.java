package com.codestates.order.dto;

import com.codestates.member.entity.Member;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@ToString
public class OrderPostDto {
    @Positive
    private long memberId;

    @Valid
    private List<OrderCoffeeDto> orderCoffees;

    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }
}
