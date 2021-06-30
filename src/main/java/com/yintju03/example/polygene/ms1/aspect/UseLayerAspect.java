package com.yintju03.example.polygene.ms1.aspect;

import com.yintju03.example.polygene.ms1.annotation.UseLayer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UseLayerAspect {
    @Around("@annotation(com.yintju03.example.polygene.ms1.annotation.UseLayer) && @annotation(ul)")
    public Object aspectAround(ProceedingJoinPoint joinPoint, UseLayer ul) throws Throwable {
        return joinPoint.proceed(new Object[]{ul.value()});
    }
}
