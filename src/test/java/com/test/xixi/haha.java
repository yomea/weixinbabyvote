package com.test.xixi;

import org.aspectj.lang.reflect.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.aspectj.TypePatternClassFilter;

public class haha {
	
	public void print(String a) {
		System.out.println(a);
	}

	public static void main(String[] args) {
		ClassFilter typePatternFilter = new TypePatternClassFilter("!execution(public com.test.xixi.haha.*(..)) && args(a)");
		System.out.println(typePatternFilter.matches(haha.class));
		
		org.aspectj.weaver.tools.PointcutExpression pointcutExpression = PointcutParser.getPointcutParserSupportingAllPrimitivesAndUsingContextClassloaderForResolution().parsePointcutExpression("!execution(public com.test.xixi.haha.*(..)) && args(a)");
//		pointcutExpression.matchesMethodCall(aMethod, callerType)
	}
}
