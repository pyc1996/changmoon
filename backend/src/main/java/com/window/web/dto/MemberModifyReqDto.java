package com.window.web.dto;

import lombok.Getter;

@Getter
public class MemberModifyReqDto {
    private String memberId;
    private String memberName;
    private String memberPassword;
    private Integer age;
    private Integer money;
}
