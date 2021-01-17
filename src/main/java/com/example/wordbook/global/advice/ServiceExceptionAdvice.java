package com.example.wordbook.global.advice;

import com.example.wordbook.global.exception.EntityNotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Aspect
public class ServiceExceptionAdvice {
    private final Logger logger = LoggerFactory.getLogger(ServiceExceptionAdvice.class);
//
////    @Around("execution(* com.example.wordbook.domain..*Service.*(..))")
//    public Object logging(ProceedingJoinPoint pjp) {
//        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//
//        HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
//
//        long startTime = System.currentTimeMillis();
//        MethodSignature serviceMethod = ((MethodSignature) pjp.getSignature());
//        String serviceName = pjp.getTarget().getClass().getSimpleName();
//
//        logger.info("##########################################################################");
//        logger.info("# ServiceName = {} | METHOD = {}  | IN_PARAMS = {}",
//                serviceName, serviceMethod.getMethod().getName(), pjp.getArgs());
//
//        Object service = null;
//        try {
//
//            service = pjp.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//
//        long endTime = System.currentTimeMillis();
//        logger.info("# RESPONSE | ServiceName = {} | TIME = {} ms | IN_PARAMS = {} | OUT_PARAMS = {}",
//                serviceName,
//                endTime - startTime,
//                pjp.getArgs(),
//                service);
//        logger.info("##########################################################################\n");
//
//        return service;
//    }
}
