package com.test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.session.Configuration;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import com.booway.service.impl.IndexServiceImpl;

public class Test5 {
	
	private Test5() {
		
		
	}
	
	public static void print() {
		
	}
	
	private Test5(Number age) {
		
	}
	
	public void print(Number age) {
		
		System.out.println(age);
	}
	
	private void te(Number age) {
		
		System.out.println(age);
	}
	
	public static void main(String[] args) throws Exception {
		
		Method method = Test5.class.getMethod("print");
		
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* com.test.*.te(..))");
		System.out.println(pointcut.matches(method, Test5.class));
		
		System.out.println(pointcut.getClassFilter().matches(Test5.class));
		
		
	}
}
