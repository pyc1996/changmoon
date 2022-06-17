package com.window.service;

import com.window.config.SecurityUtil;
import com.window.domain.Member;
import com.window.domain.MemberRepository;
import com.window.web.dto.MemberModifyReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {


    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    @Transactional(readOnly  = true)
    public Member getMemberInfo(String memberId) {
        return memberRepository.findByMemberId(memberId).orElseThrow(() -> new RuntimeException("유저없음"));
    }

    @Transactional(readOnly = true)
    public Member getMyInfo() {
        return memberRepository.findByMemberId(SecurityUtil.getCurrentMemberId()).orElseThrow(() -> new RuntimeException("해당 유저없음"));
    }

    @Transactional
    public MemberResDto modify(MemberModifyReqDto reqDto) {
        Member member = memberRepository.findByMemberId(SecurityUtil.getCurrentMemberId()).orElseThrow(() -> new RuntimeException("유저 없음 "));
        String encodedPassword = passwordEncoder.encode(reqDto.getMemberPassword());
        member.modify(reqDto, encodedPassword);
        return new MemberResDto(member.getMemberId(), member.getName(), member.getAge(),
                member.getMoney(),member.getRole());
    }

    public Boolean checkDuplicateMemberId(String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }


}
