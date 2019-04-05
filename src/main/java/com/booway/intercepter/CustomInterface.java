package com.booway.intercepter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.TextSqlNode;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.util.ReflectionUtils;

import com.booway.convert.StringToBooleanConverter;
import com.booway.convert.StringToDateConverter;
import com.booway.mapper.TEnterUserMapper;
import com.booway.pojo.TEnterUser;
import com.booway.query.QueryMap;
import com.booway.query.QueryMapMark;
import com.google.gson.Gson;

//MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler
@Intercepts(@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
		RowBounds.class, ResultHandler.class }))
public class CustomInterface implements Interceptor {

	private String id = TEnterUserMapper.class.getName()+".select";
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		try {
			MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
			String statementId = mappedStatement.getId();
			Object parameterObject = invocation.getArgs()[1];
			
			if(id.equals(statementId) && parameterObject instanceof Map) {
				Map<String, Object> map = (Map<String, Object>)parameterObject;
				Object obj = map.get("queryMap");
				
				if(obj instanceof QueryMap && obj instanceof QueryMapMark ) {
					QueryMap<String, Object> queryMap = (QueryMap<String, Object>) obj;
					Object sqlObj = map.get("sql");
					
					if(sqlObj != null && sqlObj instanceof String) {
						
						String sql = (String)sqlObj ;
						
						StringBuilder sqlBuilder = new StringBuilder();
						
						for(Entry<String, Object> entry : queryMap.entrySet()) {
							String key = entry.getKey();
							Object value = entry.getValue();
							if(StringUtils.isBlank(key) || value == null) {
								continue;
							}
							
							sqlBuilder.append("and ")
											.append(key)
											.append(" like ")
											.append("concat('%', #{queryMap.")
											.append(key)
											.append("}, '%')")
											.append(" ");
							
						}
						
						if(sqlBuilder.length() > 0) {
							sqlBuilder.delete(0, "and".length());
							sql += " where" + sqlBuilder.toString();
						}
						
						List<SqlNode> sqlNodes = new ArrayList<SqlNode>();
						
						TextSqlNode textSqlNode = new TextSqlNode(sql);
						
						sqlNodes.add(textSqlNode);
						
						MixedSqlNode meMixedSqlNode = new MixedSqlNode(sqlNodes);
						
						DynamicSqlSource dynamicSqlSource = new DynamicSqlSource(mappedStatement.getConfiguration(), meMixedSqlNode);
						
						setFieldValue(mappedStatement, dynamicSqlSource, MappedStatement.class, "sqlSource");
						
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invocation.proceed();
	}
	
	private Object getFieldValue(Object target, Class<?> clazz, String fieldName) {
		try {
			Field field = ReflectionUtils.findField(clazz, fieldName);
			if(field != null) {
				field.setAccessible(true);
				return field.get(target);
			}
		
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	private void setFieldValue(Object target, Object value, Class<?> clazz, String fieldName) {
		try {
			Field field = ReflectionUtils.findField(clazz, fieldName);
			if(field != null) {
				field.setAccessible(true);
				field.set(target, value);
			}
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

	public static void main(String[] args) {
		TEnterUser user =  new TEnterUser();
		
		DefaultConversionService conversionService = new DefaultConversionService();
		
		conversionService.addConverter(new StringToDateConverter());
		conversionService.addConverter(new StringToBooleanConverter());
		MetaObject metaObject = MetaObject.forObject(user, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
		Class<?> clazz = metaObject.getGetterType("id");
		if(conversionService.canConvert(Boolean.class, clazz)) {
			Object obj = conversionService.convert("20", clazz);
			metaObject.setValue("id", obj);
		}
		
		System.out.println(new Gson().toJson(user));
		
		System.out.println(conversionService.canConvert(String.class, Date.class));
		
		Object obj = conversionService.convert("2019-04-05 00:00:00", Date.class);
		
		System.out.println(obj);
		String dateStr = "2019-04-05 00:00:00".replaceAll("(?=[0-9])\\s+(?<=[0-9])", " ").replaceAll("[^0-9]", "-");
		System.out.println(dateStr);
		Pattern pattern = Pattern.compile("\\s*?[0-9]+\\s*?[^0-9]\\s*?[0-9]+\\s*?[^0-9]\\s*?");
		
	
		
	}

}
