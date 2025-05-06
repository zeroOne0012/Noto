package com.project.noto.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;

    public JwtUtil(@Value("${jwt.secret}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(String memberId, String role) {
        // 토큰 생성
        long now = System.currentTimeMillis();
        long expirationTime = 1000 * 60 * 60; // 1시간

        return Jwts.builder()
                .setSubject(memberId)                     // 토큰 소유자 지정(토큰 내용)
                .claim("role", role)                   // 토큰 소유자의 role 지정
                .setIssuedAt(new Date(now))               // 발급 시간
                .setExpiration(new Date(now + expirationTime)) // 만료 시간(현재 시간 + 지정 시간)
                .signWith(key, SignatureAlgorithm.HS256)  // 키 + 알고리즘으로 서명
                .compact();                               // 문자열 토큰 생성
    }

    public String getMemberIdFromToken(String token) {
        // 토큰에 저장한 memberId 추출
        return Jwts.parserBuilder()
                .setSigningKey(key)         // 키 지정
                .build()
                .parseClaimsJws(token)      // JWT 파싱
                .getBody()
                .getSubject();              // subject (memberId) 추출
    }

    public String getRoleFromToken(String token) {
        // 토큰에 저장한 role 추출
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }


    public boolean validateToken(String token) {
        // 서명 일치 여부, 토큰 만료 여부 등을 확인
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
