package com.test;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.enum_test.ChildAnnotation;

@Component(value="test10")
@Service("haha")
@Repository("helloworld")
@ChildAnnotation(value="@ChildAnnotation's value", test="Test10 extends Test")
public class Test10 extends Test {
	
	private String name;
	
	@RequestMapping
	private void print() {
		
	}
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	@RequestMapping(value="xixi", method=RequestMethod.GET)
	public static class T {
		
	}
	public static void main(String[] args) throws Exception {
		
		TypeFilter typeFilter = new AnnotationTypeFilter(Controller.class);
		MetadataReaderFactory mrFactroy = new SimpleMetadataReaderFactory();
		MetadataReader reader = mrFactroy.getMetadataReader("com.test.Test10");
		boolean flag = typeFilter.match(reader, mrFactroy);
		
		System.out.println(flag);
	}
	
}
