package com.test.test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CachedIntrospectionResultsTest {
	
	public static boolean isCandidateWriteMethod(Method method) {
		String methodName = method.getName();
		Class<?>[] parameterTypes = method.getParameterTypes();
		int nParams = parameterTypes.length;
		return (methodName.length() > 3 && methodName.startsWith("set") && Modifier.isPublic(method.getModifiers()) &&
				(!void.class.isAssignableFrom(method.getReturnType()) || Modifier.isStatic(method.getModifiers())) &&
				(nParams == 1 || (nParams == 2 && int.class == parameterTypes[0])));
	}


	public static void main(String[] args) throws Exception {
		
		Class<?> clazz = CachedIntrospectionResultsTest.class;
		Method method = clazz.getDeclaredMethod("setAge", int.class);
		System.out.println(isCandidateWriteMethod(method));
	}
	
	
	
	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}



	private int age;
	
}
