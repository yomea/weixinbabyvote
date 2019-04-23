package com.booway.context;

import java.io.Serializable;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.booway.aop.T;
import com.booway.aop.T1Impl;
import com.booway.properties.CustonPropertySource;

public class CustomXmlWepApplitionContext extends XmlWebApplicationContext {

	@Override
	protected void initPropertySources() {
		super.initPropertySources();

		MutablePropertySources mutablePropertySources = this.getEnvironment().getPropertySources();

		mutablePropertySources.addLast(new CustonPropertySource("custom"));

		String[] configLocations = this.getConfigLocations();

		/*
		 * PropertySourcesPropertyResolver resolver = new
		 * PropertySourcesPropertyResolver(mutablePropertySources); int i = 0;
		 * for (String text : configLocations) { configLocations[i++] =
		 * resolver.resolvePlaceholders(text); }
		 */

		this.setConfigLocations(configLocations);

		this.getEnvironment().setRequiredProperties("xmlPrefix");

	}

	@Override
	protected ConfigurableEnvironment createEnvironment() {
		return super.createEnvironment();
	}

	@Override
	public Object getBean(String name) throws BeansException {
		return super.getBean(name);
	}

	@Override
	protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		super.postProcessBeanFactory(beanFactory);

		beanFactory.registerResolvableDependency(T.class, new T1ImplObjectFactory());

	}

	public static class T1ImplObjectFactory implements ObjectFactory<T>, Serializable {
		@Override
		public com.booway.aop.T getObject() throws BeansException {

			return new T1Impl();
		}
	}

}
