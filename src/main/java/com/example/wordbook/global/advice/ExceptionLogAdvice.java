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
public class ExceptionLogAdvice {
    private final Logger logger = LoggerFactory.getLogger(ExceptionLogAdvice.class);

    @Around("execution(* com.example.wordbook.global.exceptionHandler.GlobalExceptionHandler.*(..))")
    public Object logging(ProceedingJoinPoint pjp) {

        LocalDateTime time = LocalDateTime.now();
        MethodSignature methodSignature = ((MethodSignature) pjp.getSignature());

        logger.error("--------------------------------------------------------------------------------------------------");

        ResponseEntity<ErrorResponse> result = null;
        try {
            result = (ResponseEntity<ErrorResponse>) pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        assert result != null;

        logger.error("METHOD = {} | TIME = {} ",
                methodSignature.getMethod().getName(), time);

        logger.error("BECAUSE -> " + Objects.requireNonNull(result.getBody()).toString());
        logger.error("--------------------------------------------------------------------------------------------------");

        return result;
    }
}
