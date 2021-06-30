package com.yintju03.example.polygene.ms1.aspect;

import com.yintju03.example.polygene.ms1.annotation.InLayer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class InLayerAspect {
    @Around("@annotation(com.yintju03.example.polygene.ms1.annotation.InLayer) && @annotation(il)")
    public Object aspectAround(ProceedingJoinPoint joinPoint, InLayer il) throws Throwable {
        return joinPoint.proceed(new Object[]{il.value()});
    }
}
