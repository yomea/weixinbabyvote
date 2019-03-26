package com.test;

import org.springframework.core.annotation.AnnotatedElementUtils;

import com.test.enum_test.ParentAnnotation;

public class Test14 {

	public static void main(String[] args) {
		
		ParentAnnotation parentAnnotation = AnnotatedElementUtils.findMergedAnnotation(Test10.class, ParentAnnotation.class);
		String value = parentAnnotation.value();
		System.out.println(value);
	}
	
}
