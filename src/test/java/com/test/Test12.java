package com.test;

import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.ClassReader;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.SpringAsmInfo;
import org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor;
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

@Component(value="test10")
@Service("haha")
@Repository
public class Test12 extends Test {
	
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
		ClassReader classReader = new ClassReader(Test12.class.getClassLoader().getResourceAsStream("com/test/Test12.class"));
		AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor(Test12.class.getClassLoader());
		classReader.accept(visitor, ClassReader.SKIP_DEBUG);
		System.out.println();
	}
	
	public static class CustomClassVisitor extends ClassVisitor {

		public CustomClassVisitor(int api) {
			super(api);
			
		}
		
		@Override
		public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
			
			System.out.println(desc);
			
			return super.visitAnnotation(desc, visible);
		}
		
		@Override
		public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//			System.out.println(name);
//			System.out.println(desc);
			
			return super.visitMethod(access, name, desc, signature, exceptions);
		}
		
	}
	
}
