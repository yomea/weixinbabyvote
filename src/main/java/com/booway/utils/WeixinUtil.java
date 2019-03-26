package com.booway.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;


public class WeixinUtil {

	private  WeixinUtil() {
		throw new RuntimeException("不能实例化");
	}
	
	public static String dealSpec(String str) {
		if(StringUtils.hasText(str)) {
			return str;
		}
		return str.replace("\\", "\\\\").replace("'", "''").replace("%", "\\%"); 
	}
	
	public static boolean validationPhoneNum(String phoneNum) {
		if(isMobile(phoneNum) || isPhone(phoneNum)) {
			return true;
		} 
		return false;
	}
	
	
	public static boolean isMobile(final String str) {
	      Pattern p = null;
	      Matcher m = null;
	      boolean b = false;
	      p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
	      m = p.matcher(str);
	      b = m.matches();
	      return b;
	  }
	
	public static boolean isPhone(final String str) {
	      Pattern p1 = null, p2 = null;
	      Matcher m = null;
	      boolean b = false;
	      p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
	      p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
	      if (str.length() > 9) {
	         m = p1.matcher(str);
	         b = m.matches();
	      } else {
	          m = p2.matcher(str);
	         b = m.matches();
	      }
	      return b;
	  }
	
	public static boolean isAllowPath(String path, String[] arr) {
		
		if(arr == null || arr.length == 0 || !StringUtils.hasText(path)) {
			return true;
		}
		AntPathMatcher antPathMatcher = new AntPathMatcher();
		for(String pathStr : arr) {
			if(antPathMatcher.match(pathStr, path)) {
				return true;
			}
		}
		return false;
	}
	
	public static String generateFilePath(String basicPath) {
		if(!basicPath.endsWith("/") && !basicPath.endsWith("\\")) {
			basicPath += File.separator;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String dateStr = sdf.format(new Date());
		String finalPath = basicPath + dateStr + File.separator;
		return finalPath;
	}
	
}
