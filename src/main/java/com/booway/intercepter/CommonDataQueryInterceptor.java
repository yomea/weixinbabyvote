package com.booway.intercepter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.TextSqlNode;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.util.CollectionUtils;

import com.booway.bo.QueryCondition;
import com.booway.mapper.TEnterUserMapper;

/**
 * @author wuzhenhong
 */
@Intercepts(@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
		RowBounds.class, ResultHandler.class }))
public class CommonDataQueryInterceptor implements Interceptor {

	private String id = TEnterUserMapper.class.getName()+".select";
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		try {
			MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
			String statementId = mappedStatement.getId();
			Object parameterObject = invocation.getArgs()[1];
			
			if(id.equals(statementId) && parameterObject instanceof Map) {
				Map<String, Object> map = (Map<String, Object>)parameterObject;
				Object obj = map.get("conditions");
				
				if(obj instanceof List ) {
					List<QueryCondition> conditions = (List<QueryCondition>) obj;
					String sql = parseStr(map.get("sql"));
					
					if(StringUtils.isNotBlank(sql)) {
						
						StringBuilder sqlBuilder = new StringBuilder();
						
						if(!CollectionUtils.isEmpty(conditions)) {
							Map<String, Object> factParams = new HashMap<>();
							map.put("queryMap", factParams);
							for(QueryCondition qc : conditions) {
								
								String column = qc.getColumn();
								String operate = qc.getOperate();
								Object value = qc.getValue();
								
								factParams.put(column, value);
								
								sqlBuilder.append("and ")
												.append(column)
												.append(" ")
												.append(operate)
												.append(" ")
												.append("#{queryMap.")
												.append(column)
												.append("}")
												.append(" ");
								
							}
						}

						if(sqlBuilder.length() > 0) {
							sqlBuilder.delete(0, "and".length());
							sqlBuilder.insert(0, " where");
							sqlBuilder.insert(0, sql);
							sql = sqlBuilder.toString();
						}
						
						List<SqlNode> sqlNodes = new ArrayList<SqlNode>();
						
						TextSqlNode textSqlNode = new TextSqlNode(sql);
						
						sqlNodes.add(textSqlNode);
						
						MixedSqlNode meMixedSqlNode = new MixedSqlNode(sqlNodes);
						
						DynamicSqlSource dynamicSqlSource = new DynamicSqlSource(mappedStatement.getConfiguration(), meMixedSqlNode);
						
						MappedStatement cloneMappedStatement = mappedStatementClone(mappedStatement, dynamicSqlSource);
						invocation.getArgs()[0] = cloneMappedStatement;
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}
	
	private MappedStatement mappedStatementClone(MappedStatement origin, SqlSource sqlSource) {
		
		 MappedStatement.Builder statementBuilder = new MappedStatement.Builder(origin.getConfiguration(), origin.getId(), sqlSource, origin.getSqlCommandType())
			        .resource(origin.getResource())
			        .fetchSize(origin.getFetchSize())
			        .timeout(origin.getTimeout())
			        .statementType(origin.getStatementType())
			        .keyGenerator(origin.getKeyGenerator())
			        .keyProperty(restore(origin.getKeyProperties()))
			        .keyColumn(restore(origin.getKeyColumns()))
			        .databaseId(origin.getDatabaseId())
			        .lang(origin.getLang())
			        .resultOrdered(origin.isResultOrdered())
			        .resultSets(restore(origin.getResultSets()))
			        .resultMaps(origin.getResultMaps())
			        .resultSetType(origin.getResultSetType())
			        .flushCacheRequired(origin.isFlushCacheRequired())
			        .parameterMap(origin.getParameterMap())
			        .useCache(origin.isUseCache())
			        .cache(origin.getCache());
		
		 return statementBuilder.build();
	}
	
	private String restore(String[] origin) {
		
		if(origin == null) {
			return null;
		}
		
		return StringUtils.join(origin, ",");
	}
	
	private String parseStr(Object objIn) {
		if(objIn == null) {
			return null;
		}
		return objIn.toString();
	}

}
