package com.codestates.helper.event;

import com.codestates.member.entity.Member;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MemberRegistrationApplicationEvent extends ApplicationEvent {
    private Member member;
    public MemberRegistrationApplicationEvent(Object source) {
        super(source);
    }
}
