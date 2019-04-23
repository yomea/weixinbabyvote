package com.test.test2;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.core.MethodParameter;

public class T<E> {
	
	public <T> List<T> t(T t, List<List<Integer>> List) {
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		class haha{};
		haha h = new haha();
		Method method = T.class.getMethod("t", Object.class, List.class);
		 TypeVariable<Method>[] tvs = method.getTypeParameters();
		 Arrays.stream(tvs).forEach(x->System.out.println(x.getName()));
		 /*Type returnType = method.getGenericReturnType();
		 System.out.println(tvs[0].equals(returnType));*/
		 
		 System.out.println("sdgwerthq2pemas;dgmwejas".hashCode()%10);
		 
		 MethodParameter mp = new MethodParameter(method, 1, 3);
		 
		 System.out.println(mp.getNestedParameterType());
		
		 T<String> t = new T<String>();
		 TypeVariable[] tttt = t.getClass().getTypeParameters();
		 
	}
	
	/*private class test extends T {
		
		public Long t() {
			return null;
		}
		
	}*/

}
