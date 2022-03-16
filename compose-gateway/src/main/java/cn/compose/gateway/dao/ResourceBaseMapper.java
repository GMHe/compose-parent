package cn.compose.gateway.dao;

import cn.compose.gateway.entity.ResourceBase;
import cn.compose.gateway.entity.ResourceBaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceBaseMapper {
    long countByExample(ResourceBaseExample example);

    int deleteByExample(ResourceBaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ResourceBase record);

    int insertSelective(ResourceBase record);

    List<ResourceBase> selectByExample(ResourceBaseExample example);

    ResourceBase selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ResourceBase record, @Param("example") ResourceBaseExample example);

    int updateByExample(@Param("record") ResourceBase record, @Param("example") ResourceBaseExample example);

    int updateByPrimaryKeySelective(ResourceBase record);

    int updateByPrimaryKey(ResourceBase record);
}