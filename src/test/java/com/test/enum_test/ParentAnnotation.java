package com.test.enum_test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParentAnnotation {
	
	public String value() default "default";
	
	public String test();
//	@AliasFor(value = "value", annotation=ChildAnnotation.class)
	public String xixi() default "xxxxxxxx";
	
}
