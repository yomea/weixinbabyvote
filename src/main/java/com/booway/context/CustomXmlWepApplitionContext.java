package com.booway.context;

import org.springframework.beans.BeansException;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.booway.properties.CustonPropertySource;

public class CustomXmlWepApplitionContext extends XmlWebApplicationContext {

	@Override
	protected void initPropertySources() {
		super.initPropertySources();
		
		MutablePropertySources mutablePropertySources = this.getEnvironment().getPropertySources();
		
		mutablePropertySources.addLast(new CustonPropertySource("custom"));
		
		String[] configLocations = this.getConfigLocations();
		
		/*PropertySourcesPropertyResolver resolver = new PropertySourcesPropertyResolver(mutablePropertySources);
		int i = 0;
		for (String text : configLocations) {
			configLocations[i++] = resolver.resolvePlaceholders(text);
		}*/
		
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
	
}
