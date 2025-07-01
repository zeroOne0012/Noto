package com.project.noto.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender; // aplication.properties에 설정한 SMTP 정보 기반으로 작동
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendPasswordResetEmail(String to, String tempPassword){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("dmb5627@naver.com");
        message.setSubject("[Noto] 비밀번호 재설정 안내");
        message.setText("임시 비밀번호: " + tempPassword + "\n로그인 후 반드시 비밀번호를 변경해주세요.");
        mailSender.send(message);
    }
}
