package cn.compose.admin.dao;

import cn.compose.admin.entity.Source;
import cn.compose.admin.entity.SourceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SourceMapper {
    long countByExample(SourceExample example);

    int deleteByExample(SourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Source record);

    int insertSelective(Source record);

    List<Source> selectByExample(SourceExample example);

    Source selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Source record, @Param("example") SourceExample example);

    int updateByExample(@Param("record") Source record, @Param("example") SourceExample example);

    int updateByPrimaryKeySelective(Source record);

    int updateByPrimaryKey(Source record);
}