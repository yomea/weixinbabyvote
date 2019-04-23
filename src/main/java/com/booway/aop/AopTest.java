package com.booway.aop;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.booway.service.IndexService;

@Component
@Aspect
public class AopTest {
	
	@Autowired
	public AopTest(T2 t, TestBean<String> testBean) {
//		t.t2();
	}
	
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