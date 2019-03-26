package com.test;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class Test7 extends PathMatchingResourcePatternResolver {
	
	public static void main(String[] args) throws Exception {
		
		Test7 test = new Test7();
		
		System.out.println(test.determineRootDir("classpath:spring/config-*/springContext-*.xml"));
		
		
	}
}
