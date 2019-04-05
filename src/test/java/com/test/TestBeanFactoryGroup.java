package com.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.ApplicationContext;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

public class TestBeanFactoryGroup {

	public static void main(String[] args) {
		
		BeanFactoryLocator beanFactoryLocator = ContextSingletonBeanFactoryLocator.getInstance("spring/rootContext.xml");
		
		BeanFactoryReference beanFactoryReference = beanFactoryLocator.useBeanFactory("root_context");
		
		ApplicationContext applicationContext = (ApplicationContext)beanFactoryReference.getFactory();
		
		System.out.println(applicationContext.getBean("hello"));
		
	}
	
}
