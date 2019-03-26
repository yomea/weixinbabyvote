package com.booway.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import com.booway.beanfactorypostprocessor.TestBeanFactoryPostProcessor;

public class TestInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		
		applicationContext.addBeanFactoryPostProcessor(new TestBeanFactoryPostProcessor());
		
	}

}
