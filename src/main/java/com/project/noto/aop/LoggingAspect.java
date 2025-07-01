package com.project.noto.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger("API_LOGGER");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Controller 패키지 하위의 모든 메서드 대상
    @Pointcut("execution(* com.project.noto.controller..*(..))")
    public void controllerMethods() {}

    @Around("controllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        try {
            String jsonArgs = objectMapper.writeValueAsString(args);
            logger.info("➡️ API 요청: {}", buildJsonLog("API Request", methodName, jsonArgs));

            Object result = joinPoint.proceed();

            String jsonResult = objectMapper.writeValueAsString(result);
            logger.info("✅ API 응답: {}", buildJsonLog("API Response", methodName, jsonResult));

            return result;
        } catch (Throwable ex) {
            logger.error("❌ API 예외: {}", buildJsonLog("API Error", methodName, ex.getMessage()));
            throw ex;
        }
    }

    private String buildJsonLog(String event, String method, String data) throws JsonProcessingException {
        objectMapper.enable(com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT);

        Map<String, Object> logMap = new LinkedHashMap<>();
        logMap.put("event", event);
        logMap.put("method", method);
        logMap.put("data", data);
        logMap.put("timestamp", LocalDateTime.now().toString());

        return objectMapper.writeValueAsString(logMap);
    }
}
