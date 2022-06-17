package com.window.web.dto;

import com.window.domain.Member;
import com.window.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSignUpResDto {

    private String memberId;
    private String memberName;
    private Integer age;
    private Integer money;
    private Role role;

    public static MemberSignUpResDto of(Member member) {
        return new MemberSignUpResDto(member.getMemberId(), member.getName(),
                member.getAge(), member.getMoney(), member.getRole());
    }



}
