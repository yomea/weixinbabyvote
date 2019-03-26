package com.booway.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date> {

	private String datePattern = "yyyy-MM-dd HH:mm:ss";
	
	public DateFormatter(String datePattern) {
		this.datePattern = datePattern;
	}
	

	@Override
	public String print(Date date, Locale locale) {
		SimpleDateFormat sdf = new SimpleDateFormat(this.datePattern);
		String dateStr = sdf.format(date);
		return dateStr;
	}

	@Override
	public Date parse(String dateStr, Locale locale) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(this.datePattern);
		Date date = sdf.parse(dateStr);
		return date;
	}


}
