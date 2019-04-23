package com.test.test;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class TestConstructorResolver {

	public static void main(String[] args) throws Exception {
		
		Class<?> clazz = Class.forName("org.springframework.beans.factory.support.ConstructorResolver");
		AbstractAutowireCapableBeanFactory beanFactory = new DefaultListableBeanFactory();
		Constructor<?> constructor =  clazz.getConstructor(AbstractAutowireCapableBeanFactory.class);
		constructor.setAccessible(true);
		Object constructorResolver = constructor.newInstance(beanFactory);
		
		//instantiateUsingFactoryMethod(beanName, mbd, explicitArgs);
		//autowireConstructor(beanName, mbd, ctors, explicitArgs);
		
		Method  instantiateUsingFactoryMethod = clazz.getDeclaredMethod("instantiateUsingFactoryMethod", String.class, RootBeanDefinition.class, Object[].class);
		
		
		
	}
	
	
}
