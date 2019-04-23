package com.test.xixi;

import org.aspectj.weaver.patterns.BasicTokenSource;
import org.aspectj.weaver.patterns.IToken;
import org.aspectj.weaver.patterns.ITokenSource;

public class Xixi {
	
	public static void main(String[] args) {
		
		ITokenSource tokenSource = BasicTokenSource.makeTokenSource("* com.spring.service.BusinessObject.businessService(java.lang.String,..)", null);
		StringBuilder sbStr = new StringBuilder();
		for(IToken itoken = tokenSource.next(); !itoken.getString().equals("<eof>");itoken = tokenSource.next()) {
			sbStr.append(itoken.getString());
		}
		
		System.out.println(sbStr.toString());
	}
	
}
