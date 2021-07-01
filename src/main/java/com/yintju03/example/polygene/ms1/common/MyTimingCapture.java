package com.yintju03.example.polygene.ms1.common;

import java.lang.reflect.Method;

import org.apache.polygene.api.concern.GenericConcern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTimingCapture extends GenericConcern {
    private static final Logger logger = LoggerFactory.getLogger(MyTimingCapture.class);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = next.invoke(proxy, method, args);
        logger.info("invoke, proxy = {}, method = {}, takes {} ms, result = {}", proxy, method.getName(), System.currentTimeMillis() - start, result);
        return result;
    }
    
}
