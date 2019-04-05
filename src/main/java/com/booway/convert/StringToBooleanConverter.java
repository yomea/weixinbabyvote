package com.booway.convert;

import org.springframework.core.convert.converter.Converter;

public class StringToBooleanConverter implements Converter<String, Boolean> {

		@Override
		public Boolean convert(String source) {
			try {
				
				return Boolean.valueOf(source);
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}