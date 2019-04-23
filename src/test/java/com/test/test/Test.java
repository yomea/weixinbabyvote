package com.test.test;

import java.util.Map;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;

import com.booway.convert.StringToBooleanConverter;
import com.booway.convert.StringToDateConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test {

	public static void main(String[] args) {
		
		String str = "{\"id\":\"3\", \"date\":\"2019sdg01dg05\", \"name\":\"root\",\"user\":{}}";
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();
		User user = null;
		try {
		
			user = gson.fromJson(str, User.class);
		}catch(Exception e) {
			System.err.println("解析出错啦!");
		}
		
		if(user == null) {
			user = new User();
		}
		
		CommonConversionService ccs = new CommonConversionService();
		ccs.addConverter(new StringToBooleanConverter());
		ccs.addConverter(new StringToDateConverter());
		
		MetaObject metaObject = MetaObject.forObject(user, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
		
		@SuppressWarnings("unchecked")
		java.util.Map<String, Object> map =  gson.fromJson(str, java.util.Map.class);
		
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			
			String key = entry.getKey();
			Object value = entry.getValue();
			
			if(metaObject.hasGetter(key)) {
				Class<?> clazz = metaObject.getGetterType(key);
				
//				if(ccs.canConvert(value.getClass(), clazz)) {
					Object result = ccs.convert(value, clazz);
					metaObject.setValue(key, result);
//				}
				
			}
		
		}
		
		System.out.println(user != null ? gson.toJson(user) : null);
		
	}
	
	
	
}
