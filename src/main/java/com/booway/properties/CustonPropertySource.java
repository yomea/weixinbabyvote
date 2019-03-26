package com.booway.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.PropertySource;

public class  CustonPropertySource extends PropertySource<Map<String, String>> {
	
	private static final Map<String, String> MAP = new HashMap<String, String>();
	
	static {
		MAP.put("xmlPrefix", "springContext");
		
	}

	public CustonPropertySource(String name) {
		super(name);
	}

	@Override
	public Object getProperty(String name) {
	
		return MAP.get(name);
	}

}
