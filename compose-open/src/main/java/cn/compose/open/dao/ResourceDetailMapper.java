package cn.compose.open.dao;

import cn.compose.open.entity.ResourceDetail;
import cn.compose.open.entity.ResourceDetailExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceDetailMapper {
    long countByExample(ResourceDetailExample example);

    int deleteByExample(ResourceDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ResourceDetail record);

    int insertSelective(ResourceDetail record);

    List<ResourceDetail> selectByExample(ResourceDetailExample example);

    ResourceDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ResourceDetail record, @Param("example") ResourceDetailExample example);

    int updateByExample(@Param("record") ResourceDetail record, @Param("example") ResourceDetailExample example);

    int updateByPrimaryKeySelective(ResourceDetail record);

    int updateByPrimaryKey(ResourceDetail record);
}