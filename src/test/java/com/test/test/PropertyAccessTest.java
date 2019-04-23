package com.test.test;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeanWrapperImpl;

public class PropertyAccessTest {

	public static void main(String[] args) {
		
//		int nestedInt = PropertyAccessorUtils.getFirstNestedPropertySeparatorIndex("a[1].b");
//		System.out.println(nestedInt);
		
		BeanWrapperImpl beanWrapper = new BeanWrapperImpl();
		
		beanWrapper.setWrappedInstance(new User());
		
		beanWrapper.setAutoGrowNestedPaths(true);
		
		PropertyDescriptor propertyDescriptor = beanWrapper.getPropertyDescriptor("list[1][2].name");
		
		System.out.println(propertyDescriptor.getName());
		
	}
	
}
