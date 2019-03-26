package com.booway.aop;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Component
@Configuration
@ComponentScan("com.test")
public class ConfigTest {

	@PostConstruct
	public void init() {
		System.out.println("init");
	}
	
}
