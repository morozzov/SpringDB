package com.example.springdb.advice;

import com.example.springdb.entity.PlayerEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AdviceProcessor {

    @Pointcut("@annotation(com.example.springdb.advice.Audit)")
    public void annotated() {
    }


    @Pointcut("execution(public * com.example.springdb.service.PlayerService.*(..))")
    public void playerServiceMethods() {
    }


    @Before("annotated()")
    public void printMethodInfo(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String name = jp.getSignature().getName();
        Class declaringType = jp.getSignature().getDeclaringType();
        log.info("aspect() - method {}.{} called with params: {}", declaringType, name, args);
    }

    @AfterReturning(value = "playerServiceMethods()", returning = "entity")
    public void printAllMethodsReturns(JoinPoint jp, PlayerEntity entity) {
        Object[] args = jp.getArgs();
        String name = jp.getSignature().getName();
        Class declaringType = jp.getSignature().getDeclaringType();
        log.info("aspect() - method {}.{} called with params: {}. Returned: {}", declaringType, name, args, entity);
    }
}
