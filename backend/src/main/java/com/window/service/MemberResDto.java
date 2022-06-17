package com.window.service;

import com.window.domain.Member;
import com.window.domain.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResDto {

    private String memberId;
    private String name;
    private Integer age;
    private Integer money;
    private Role role;

    public MemberResDto(String memberId, String name, Integer age, Integer money, Role role) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.money = money;
        this.role = role;
    }




}
