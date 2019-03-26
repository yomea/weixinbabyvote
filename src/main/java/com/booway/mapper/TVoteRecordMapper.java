package com.booway.mapper;

import com.booway.pojo.TVoteRecord;
import com.booway.pojo.TVoteRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TVoteRecordMapper {
    int countByExample(TVoteRecordExample example);

    int deleteByExample(TVoteRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TVoteRecord record);

    int insertSelective(TVoteRecord record);

    List<TVoteRecord> selectByExample(TVoteRecordExample example);

    TVoteRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TVoteRecord record, @Param("example") TVoteRecordExample example);

    int updateByExample(@Param("record") TVoteRecord record, @Param("example") TVoteRecordExample example);

    int updateByPrimaryKeySelective(TVoteRecord record);

    int updateByPrimaryKey(TVoteRecord record);
}