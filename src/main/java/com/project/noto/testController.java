package com.project.noto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

// 기본 테스트 API
// GET http://localhost:8080/test -> DB를 거쳐 현재 시간 반환
@RestController 
@RequiredArgsConstructor
public class testController {
    private final TestMapper testMapper;
    @GetMapping("/test")
    public ResponseEntity<String> getMethodName() {
        Object time = testMapper.getTime();
        return ResponseEntity.ok(time.toString());
    }   
}
@Mapper
interface TestMapper {
    @Select("SELECT now()")
    Object getTime();
}
