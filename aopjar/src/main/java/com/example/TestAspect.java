package com.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TestAspect {
  static {
    // In our aspect, we need the static reference of a class to happen before webapp is loaded
    // We thin weaving is causing this to get loaded first but it might just be luck
    SqlInterceptorRegistry.getInstance();
  }

  @Around("call(* java.sql.*Statement.execute*(..))")
  public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable {
    SqlInterceptor instance = SqlInterceptorRegistry.getInstance().get();
    instance.start();
    Object result = joinPoint.proceed();
    instance.end();
    System.err.println(joinPoint.toShortString() + ": " + result);
    return result;
  }

}
