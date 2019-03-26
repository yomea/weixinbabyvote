package com.test;

import java.util.HashMap;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

public class Test3 {
	
	private Integer age = 100;
	
	public static void main(String[] args) throws Exception {
		
		  SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(new Configuration());
		    SqlSource sqlSource = sqlSourceParser.parse("select * from test where age > #{age, javaType=int, jdbcType=INTEGER}", Integer.class, new HashMap());
		    BoundSql boundSql = sqlSource.getBoundSql(20);
		    
		    System.out.println(boundSql.getSql());
		
		    System.out.println(boundSql.getParameterMappings());
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
