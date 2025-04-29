package com.project.noto.service;

import com.project.noto.mapper.MemberMapper;
import com.project.noto.domain.Member;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(Member member) {
        Member existing = memberMapper.findByMemberId(member.getMemberId());
        if (existing != null) {
            throw new RuntimeException("이미 존재하는 사용자입니다.");
        }
        member.setPassword(passwordEncoder.encode(member.getPassword())); // 암호화
        memberMapper.insertMember(member);
    }

    public Member login(String memberId, String password) {
        Member member = memberMapper.findByMemberId(memberId);
        if (member == null) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        return member;
    }
}
