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

public class Test6 {
	
	private Test6() {
		
		
	}
	
	public static void print() {
		
	}
	
	private Test6(Number age) {
		
	}
	
	public void print(Number age) {
		
		
	}
	
	private void te(Number age) {
		
		System.out.println(age);
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(System.getProperties());
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println(System.getenv());
		
		
	}
}
