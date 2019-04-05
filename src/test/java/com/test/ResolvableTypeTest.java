package com.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.ResolvableType;

public class ResolvableTypeTest {
	
	
	public<T> void print(List<T> list, Map<Integer, String> map) {
		
		
	}

	public static void main(String[] args) throws Exception {
		
		List<String> list = new ArrayList<>();
		Method method =  ResolvableTypeTest.class.getMethod("print", List.class, Map.class);
		
		ResolvableType resolvableType = ResolvableType.forType(method.getGenericParameterTypes()[1]);
		System.out.println(resolvableType.resolve());
		System.out.println(resolvableType.getGenerics()[1].resolve());
		
		System.out.println(ResolvableType.forClass(String.class).as(Number.class));
		
		
	}
	
}
