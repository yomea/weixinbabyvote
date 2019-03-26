package com.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.AntPathMatcher;

public class Test8 extends PathMatchingResourcePatternResolver {
	
	public static void main(String[] args) throws Exception {
		//
		AntPathMatcher matcher = new AntPathMatcher();
		
//		String str = "spring/springContext-*?{{a}{b}}{a}{\\{}.xml";
		String str = "spring/**/**/a/**/**/springContext-*.xml";
		
		System.out.println(matcher.match(str, "spring/config/haha/a/springContext-dao.xml"));
		
		
		
		Pattern pattern = Pattern.compile("\\?|\\*|\\{((?:\\{[^/]+?\\}|[^/{}]|\\\\[{}])+?)\\}");
		
		Matcher m = pattern.matcher(str);
		
		while(m.find()) {
			System.out.println(m.group());
		}
		
		
	}
}
