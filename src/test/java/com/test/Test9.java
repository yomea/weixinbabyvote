package com.test;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.core.type.filter.AspectJTypeFilter;

public class Test9 {
	static {
		System.out.println("hello world");
	}
	
	public static void main(String[] args) throws Exception {
		
		AspectJTypeFilter typeFilter = new AspectJTypeFilter("*..*", Test9.class.getClassLoader());
		MetadataReader smr = new SimpleMetadataReaderFactory().getMetadataReader("com.test.Test");
		
		boolean flag = typeFilter.match(smr, new SimpleMetadataReaderFactory());
		System.out.println(flag);
	}
	
}
