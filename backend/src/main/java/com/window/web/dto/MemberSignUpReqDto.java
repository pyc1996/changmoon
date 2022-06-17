package com.window.web.dto;

import com.window.domain.Member;
import com.window.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignUpReqDto {

    private String memberId;
    private String memberPassword;
    private String memberName;
    private Integer age;
    private Integer money;
    private Role role;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .memberId(memberId)
                .memberPassword(passwordEncoder.encode(memberPassword))
                .name(memberName)
                .age(age)
                .money(money)
                .role(Role.ROLE_USER)
                .build();
    }



}
