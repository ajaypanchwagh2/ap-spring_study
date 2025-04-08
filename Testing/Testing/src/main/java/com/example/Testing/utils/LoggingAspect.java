package com.example.Testing.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Pointcut for all methods inside Calculate class
    @Pointcut("execution(* com.example.Testing.utils.Calculate.*(..))")
    public void allCalculateMethods() {}

    @Before("allCalculateMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("🟡 Before: " + joinPoint.getSignature().getName());
    }

    @After("allCalculateMethods()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("🟢 After: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "allCalculateMethods()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println("✅ Returned from: " + joinPoint.getSignature().getName() + " with result: " + result);
    }

    @AfterThrowing(pointcut = "allCalculateMethods()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        System.out.println("❌ Exception in: " + joinPoint.getSignature().getName() + " - " + ex.getMessage());
    }
}
