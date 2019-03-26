package com.booway.utils;

public class ParseUtil {

	private ParseUtil() {}
	
	public static String parseStr(Object obj) {
		String temp = "";
		if(obj == null) {
			return temp;
		}
		if(obj instanceof String) {
			return (String)obj;
		}
		return String.valueOf(obj.toString());
		
	}
	
	public static int parseInt(Object obj, int defaultVal) {
		int temp = defaultVal;
		if(obj == null) {
			return temp;
		}
		if(obj instanceof Number) {
			return ((Number)obj).intValue();
		}
		try {
			temp = Integer.valueOf(obj.toString());
		}catch(Exception e) {
		}
		return temp;
	}
	
}
