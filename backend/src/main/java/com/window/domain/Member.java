package com.window.domain;

import com.window.service.MemberResDto;
import com.window.web.dto.MemberModifyReqDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_no")
    private Long no;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_password")
    private String memberPassword;

    @Column(name = "member_age")
    private Integer age;

    @Column(name = "member_money")
    private Integer money;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member() {}

    @Builder
    public Member(Long no, String memberId, String name, String memberPassword, Integer age, Integer money, Role role) {
        this.no = no;
        this.memberId = memberId;
        this.name = name;
        this.memberPassword = memberPassword;
        this.age = age;
        this.money = money;
        this.role = role;
    }

    public void modify(MemberModifyReqDto reqDto, String encodedPassword) {
        this.memberId = reqDto.getMemberId();
        this.name = reqDto.getMemberName();
        this.memberPassword = encodedPassword;
        this.age = reqDto.getAge();
        this.money = reqDto.getMoney();
    }

    public static MemberResDto toResDto(Member member) {
        return null;
    }



}

