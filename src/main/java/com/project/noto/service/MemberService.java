package com.project.noto.service;

import com.project.noto.mapper.MemberMapper;
import com.project.noto.domain.Member;
import com.project.noto.service.MailService;
import org.springframework.stereotype.Service;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder, MailService mailService) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    public void signup(Member member) {
        Member existing = memberMapper.findByMemberId(member.getMemberId());
        if (existing != null) {
            throw new RuntimeException("이미 존재하는 사용자입니다.");
        }
        member.setPassword(passwordEncoder.encode(member.getPassword())); // 암호화
        member.setRole("ROLE_USER");
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

    public void resetPassword(String email) {
        Member member = memberMapper.findByEmail(email);
        if (member == null) throw new RuntimeException("등록된 이메일이 없습니다.");

        String tempPassword = generateTempPassword();
        member.setPassword(passwordEncoder.encode(tempPassword));
        memberMapper.updatePassword(member.getMemberId(), member.getPassword());

        mailService.sendPasswordResetEmail(email, tempPassword);
    }

    private String generateTempPassword() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}
