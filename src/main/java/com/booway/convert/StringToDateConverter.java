package com.booway.convert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class StringToDateConverter implements Converter<String, Date> {

		private static Pattern pattern = Pattern.compile("([0-9]+)[^0-9]+?([0-9]+)[^0-9]+?([0-9]+)(?:[^0-9]+?([0-9]+)[^0-9]+?([0-9]+)[^0-9]+?([0-9]+))?");
	
		@Override
		public Date convert(String source) {
			try {
				if(!StringUtils.hasText(source)) {
					return null;
				}
				source = source.trim();
				
				return doConvert(source);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		private Date doConvert(String source) throws Exception {
			
			Matcher matcher = pattern.matcher(source);
			
			if(matcher.matches()) {
				String year = matcher.group(1);
				String month = matcher.group(2);
				String day = matcher.group(3);
				String hour = matcher.group(4);
				String minute =  matcher.group(5);
				String second = matcher.group(6);
				
				hour = hour == null ? "00" : hour;
				minute = minute == null ? "00" : minute;
				second = second == null ? "00" : second;
			
				
				String[] timeElements = new String[]{year, month, day, hour, minute, second};
				String[] patternDesc = new String[]{"y", "M", "d", "H", "m", "s"};
				
				StringBuilder adjustStr = new StringBuilder();
				StringBuilder timePattern = new StringBuilder();
				
				for(int i = 0; i < timeElements.length; i++) {
					String timeEle = timeElements[i];
					adjustStr.append(timeEle).append("-");
					for(int j = 0; j < timeEle.length(); j++) {
						timePattern.append(patternDesc[i]);
					}
					timePattern.append("-");
					
				}
				
				adjustStr.deleteCharAt(adjustStr.length() - 1);
				timePattern.deleteCharAt(timePattern.length() - 1);
				
				SimpleDateFormat sdf = new SimpleDateFormat(timePattern.toString());
				
				Date date = sdf.parse(adjustStr.toString());
				
				return date;
			}
			
			return null;
		}
		
	}