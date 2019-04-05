package com.booway.aop;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Component
@Configuration
@ComponentScan("com.test")
public class ConfigTest {

	@PostConstruct
	public void init() {
		System.out.println("init");
	}
	
	@Bean(name = "niHao")
	public String createString() {
		return new String("ni hao!");
	}
	
}
