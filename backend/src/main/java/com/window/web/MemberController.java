package com.window.web;

import com.window.domain.Member;
import com.window.service.MemberResDto;
import com.window.service.MemberService;
import com.window.web.dto.MemberModifyReqDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private final MemberService memberService;

    @ApiOperation(value = "내 정보 보기 ", notes = "내 정보 보기")
    @GetMapping("/me")
    public ResponseEntity<Member> getMyInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @ApiOperation(value = "회원수정")
    @PutMapping("/modify")
    public ResponseEntity<MemberResDto> modify(@RequestBody MemberModifyReqDto requestDto) {
        return ResponseEntity.ok(memberService.modify(requestDto));
    }

    /*
    컬럼 이름을 쓸 때는
' (single quote) 가 아니라
` (backticks)를 써야 한다고 함
     */
    @ApiOperation(value = "아이디 중복 체크")
    @GetMapping("/duplicate/{memberId}")
    public ResponseEntity<Boolean> checkDuplicate(@PathVariable String memberId) {

        boolean result = memberService.checkDuplicateMemberId(memberId);
        return ResponseEntity.ok(result);
    }




}
