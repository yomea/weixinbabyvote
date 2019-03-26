package com.booway.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopTest {
	
	@Pointcut("execution(* com.booway.service.*.*(..))")
	public void poincut() {
		
	}
	
	@Before("poincut()")
	public void before() {
		
		System.out.println("AopTest before ...");
		
	}
	
public void after() {
		
		System.out.println("AopTest after ...");
		
	}

public void around(ProceedingJoinPoint joinpoint) {
	System.out.println("AopTest around before ...");
	
	try {
		Object returnVal = joinpoint.proceed();
		System.out.println(returnVal);
	} catch (Throwable e) {
		e.printStackTrace();
	}
	System.out.println("AopTest around after ...");
}
	
	
}