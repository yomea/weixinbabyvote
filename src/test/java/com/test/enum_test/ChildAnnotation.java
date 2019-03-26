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
@ParentAnnotation(value="test1", test="i am 測試")
public @interface ChildAnnotation {
//	@AliasFor(value = "xixi", annotation=ParentAnnotation.class)
	public String value() default "i want override parent's value";
	
	//@AliasFor(value = "value", annotation=ParentAnnotation.class)
	public String xixi() default "i want override parent's value";
	
	@AliasFor(value = "value", annotation=ParentAnnotation.class)
	public String test() default "";
}
