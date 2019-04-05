package com.test.test;

import com.booway.convert.StringToBooleanConverter;
import com.booway.convert.StringToDateConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test {

	public static void main(String[] args) {
		
		String str = "{\"id\":\"3\", \"date\":\"2019-01-01 00:00:00\"}";
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();
		User user = null;
		try {
		
			user = gson.fromJson(str, User.class);
		}catch(Exception e) {
			
		}
		
		CommonConversionService ccs = new CommonConversionService();
		ccs.addConverter(new StringToBooleanConverter());
		ccs.addConverter(new StringToDateConverter());
		
		
		System.out.println(user != null ? gson.toJson(user) : null);
		
	}
	
	
	
}
