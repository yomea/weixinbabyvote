package com.test.test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class IntrospectorTest {

	public static void main(String[] args) throws Exception {
		
		BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
		
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		
		for(PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			System.out.println(propertyDescriptor.getName());
		}
	}
	
}
