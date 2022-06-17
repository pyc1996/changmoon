package com.window.web;

import com.window.config.TokenDto;
import com.window.service.AuthService;
import com.window.web.dto.MemberLoginReqDto;
import com.window.web.dto.MemberSignUpReqDto;
import com.window.web.dto.MemberSignUpResDto;
import com.window.web.dto.TokenRequestDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value = "회원가입", notes = "멤버 회원가입")
    @PostMapping(value = "/signup", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<MemberSignUpResDto> signup(@RequestBody MemberSignUpReqDto memberSignUpReqDto) {

        return ResponseEntity.ok(authService.signup(memberSignUpReqDto));
    }

    @ApiOperation(value = "로그인", notes = "멤버 로그인")
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberLoginReqDto memberLoginReqDto) {
        return ResponseEntity.ok(authService.login(memberLoginReqDto));
    }

    @ApiOperation(value = "토큰재발행", notes = "토큰 재발행")
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }


}
