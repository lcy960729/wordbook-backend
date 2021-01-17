package com.example.wordbook.global.advice;

import com.example.wordbook.global.dto.ErrorResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Aspect
public class ControllerLogAdvice {
    private final Logger logger = LoggerFactory.getLogger(ControllerLogAdvice.class);

    @Around("execution(* com.example.wordbook.domain.*.controller.*.*(..))")
    public Object logging(ProceedingJoinPoint pjp) {

        LocalDateTime time = LocalDateTime.now();
        Long startTime = System.currentTimeMillis();

        MethodSignature methodSignature = ((MethodSignature) pjp.getSignature());

        logger.info("--------------------------------------------------------------------------------------------------");
        logger.info("METHOD = {} | IN_ARGS = {} | TIME = {} ",
                methodSignature.getMethod().getName(), pjp.getArgs(), time);

        ResponseEntity<Object> result = null;
        try {
            result = (ResponseEntity<Object>) pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        Long endTime = System.currentTimeMillis();
        logger.info("TIME = " + (endTime-startTime));
        logger.info("--------------------------------------------------------------------------------------------------");

        return result;
    }
}
