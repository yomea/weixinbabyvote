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

public class Test4 {
	
	private Test4() {
		
		
	}
	
	private Test4(Number age) {
		
	}
	
	private void print(Number age) {
		
		System.out.println(age);
	}
	
	public static void main(String[] args) throws Exception {
		
		/*Class<?> clazz = Test4.class;
		Method method = clazz.getDeclaredMethod("print", Integer.class);
		System.out.println(method);*/
		
		DefaultObjectFactory defObjFactory = new DefaultObjectFactory();
		
		Class<?> clazz = Integer.class;
		
		List<Class<?>> paramsType = Arrays.asList(clazz);
		
		Test4 test4 = defObjFactory.create(Test4.class, paramsType, Arrays.asList((Object)10));
		System.out.println(test4.getClass());
	}
}
