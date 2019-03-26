package com.booway.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.booway.Exception.RestClientException;

public class BeanCopyUtil {
	
	public static<T> T copy(Class<T> clazz, Object src) {
		
		Class<?> srcClss = src.getClass();
		Field[] fields = srcClss.getDeclaredFields();
		T target = null;
		try {
			target = clazz.newInstance();
		}catch(Exception e) {
			throw new RestClientException("java 数据模型必须有无参构造方法");
		}
		for(Field field : fields) {
			//字段名
			String fieldName = field.getName();
			Class<?> clss =  field.getType();
			String setMethodName = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
			String getMethodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
			try {
				Method setmethod = srcClss.getDeclaredMethod(setMethodName, clss);
				Method getmethod = srcClss.getDeclaredMethod(getMethodName);
				Object val = getmethod.invoke(src);
				if(target != null) {
					setmethod.invoke(target, val);
				}
			} catch (Exception e) {
				//没有setter方法的属性不予理睬
				continue;
			}
			
			
		}
		return target;
	}
}
