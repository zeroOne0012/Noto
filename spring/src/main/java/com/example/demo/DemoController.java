package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DemoController {
    private final DemoService demoService;
    @GetMapping("/demo")
    public ResponseEntity<List<Demo>> demo(){
        List<Demo> result = demoService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
