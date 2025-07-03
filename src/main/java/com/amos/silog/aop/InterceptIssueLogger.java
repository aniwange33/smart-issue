package com.amos.silog.aop;


import com.amos.silog.annotation.IssueLogger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
public class InterceptIssueLogger {
    @Around("@annotation(com.amos.silog.annotation.IssueLogger)")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("before Intercepting issue");
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        if (method.isAnnotationPresent(IssueLogger.class)) {
            IssueLogger issueLogger = method.getAnnotation(IssueLogger.class);
            assert issueLogger != null;
            log.info("{} is executing ...", issueLogger.name());
        }
        Object proceed = joinPoint.proceed();
        log.info("after intercepting issue");
        return proceed;
    }
}
