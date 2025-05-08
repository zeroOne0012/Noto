package com.project.noto.controller;

import com.project.noto.dto.LoginRequest;
import com.project.noto.dto.PasswordResetRequest;
import com.project.noto.jwt.JwtUtil;
import com.project.noto.domain.Member;
import com.project.noto.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(MemberService memberService, JwtUtil jwtUtil) {
        this.memberService = memberService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid Member member) {
        memberService.signup(member);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        Member member = memberService.login(loginRequest.getMemberId(), loginRequest.getPassword());
        String token = jwtUtil.generateToken(member.getMemberId(), member.getRole());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid PasswordResetRequest request) {
        memberService.resetPassword(request.getEmail());
        return ResponseEntity.ok("임시 비밀번호가 이메일로 전송되었습니다.");
    }

}
