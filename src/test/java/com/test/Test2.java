package com.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.TypeParameterResolver;
import org.apache.ibatis.scripting.xmltags.OgnlCache;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ClassUtils;

public class Test2 {
	private static final String URL = "http://h4vtxk.natappfree.cc/weixinbabyvote/baby/authorize";
	private Integer a = 299;
	/*public static void main(String[] args) throws Exception {
		String codeurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx216574c09a6da30d&redirect_uri={0}&response_type=code&scope=snsapi_base&state=123#wechat_redirect";	
		codeurl = MessageFormat.format(codeurl, URLEncoder.encode(URL, "utf-8"));
		
		//String responStr = HttpClientUtil.doGet(codeurl);
		
		System.out.println(codeurl);
		
		//System.out.println(responStr);
		
		System.out.println(ClassUtils.isAssignable(Number.class, Integer.class));
		
		Invocation invocation = null;
		
		Object[] qq = invocation.getArgs();
		
		MappedStatement ms = (MappedStatement)qq[0];
		
		BoundSql bs = ms.getBoundSql(qq[1]);
		
		String sql = bs.getSql();
	}*/
	
	public static void main(String[] args) throws Exception {
		
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		
		Resource[] resources = pathMatchingResourcePatternResolver.getResources("classpath*:com/test/**/*.class");
		
		Resource r = resources[0];
		
		System.out.println(Test2.class.getClassLoader().getResource(""));
		
		System.out.println(r.getURL());
		
		System.out.println(r.getURL());
		
		Method method = Test2.class.getDeclaredMethod("print", new Class[]{String.class, Integer.class, long.class});
		
		Type type = TypeParameterResolver.resolveReturnType(method, Test2.class);
		
		Type[] types = TypeParameterResolver.resolveParamTypes(method, Test2.class);
		System.out.println(Arrays.toString(types));
		
		System.out.println(((ParameterizedType)method.getGenericReturnType()).getRawType());
		System.out.println(method.getReturnType());
		/*System.out.println(method.getGenericReturnType().getClass());
		
		System.out.println(method.getReturnType().getClass());
		
		System.out.println(method.getDeclaringClass());
		
		System.out.println(type);*/
		
		MetaObject metaObejct = SystemMetaObject.forObject(new Test2());
		Object a = metaObejct.getValue("a");
		System.out.println(a);
		System.out.println(String.class.toString());
	
		Object obj = OgnlCache.getValue("a==129", new Test2());
		System.out.println(obj);
		
		
	}
	
	
	
	public Integer getA() {
		return a;
	}



	public void setA(Integer a) {
		this.a = a;
	}



	public List<String> print(String a, Integer b, long c) {
		return null;
	}
	
	
}
