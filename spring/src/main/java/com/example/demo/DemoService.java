package com.example.demo;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoService {
    private final DemoMapper demoMapper;

    public List<Demo> getAll() {
        return demoMapper.findAll();
    }
}