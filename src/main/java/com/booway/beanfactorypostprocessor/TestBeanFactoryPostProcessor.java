package com.booway.beanfactorypostprocessor;

import java.util.UUID;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class TestBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition("com.booway.pojo.Authorize");
		builder.addPropertyValue("expires_in", 7000);
		builder.addPropertyValue("openid", UUID.randomUUID().toString());
		registry.registerBeanDefinition("authorize", builder.getBeanDefinition());
		
	}

	

}
