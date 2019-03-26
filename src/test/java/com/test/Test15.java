package com.test;

import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.BeanExpressionResolver;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.expression.StandardBeanExpressionResolver;

public class Test15 {

	public static void main(String[] args) {
		
		BeanExpressionResolver beanRe = new StandardBeanExpressionResolver();
		ConfigurableBeanFactory beanFactory = new DefaultListableBeanFactory();
		beanFactory.registerSingleton("a", "hello world");
		BeanExpressionContext context = new BeanExpressionContext(beanFactory, null);
		Object result = beanRe.evaluate("#{a}", context);
		
		System.out.println(result);
	}
	
}
