package com.booway.mapper;

import com.booway.pojo.TEnterUser;
import com.booway.pojo.TEnterUserExample;
import com.booway.query.QueryMap;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TEnterUserMapper {
	
	 List<LinkedHashMap<String, Object>> select(@Param("sql") String sql, @Param("queryMap") QueryMap<String, Object> queryMap);
	
    int countByExample(TEnterUserExample example);

    int deleteByExample(TEnterUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TEnterUser record);

    int insertSelective(TEnterUser record);

    List<TEnterUser> selectByExample(TEnterUserExample example);

    TEnterUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TEnterUser record, @Param("example") TEnterUserExample example);

    int updateByExample(@Param("record") TEnterUser record, @Param("example") TEnterUserExample example);

    int updateByPrimaryKeySelective(TEnterUser record);

    int updateByPrimaryKey(TEnterUser record);

	List<TEnterUser> selectSortByExample(TEnterUserExample example);

	int getSortNum(Integer voteNum);
	
	int getMaxNum();
}