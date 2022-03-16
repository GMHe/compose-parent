package cn.compose.sync.dao;

import cn.compose.sync.entity.ResourceType;
import cn.compose.sync.entity.ResourceTypeExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceTypeMapper {
    long countByExample(ResourceTypeExample example);

    int deleteByExample(ResourceTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ResourceType record);

    int insertSelective(ResourceType record);

    List<ResourceType> selectByExample(ResourceTypeExample example);

    ResourceType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ResourceType record, @Param("example") ResourceTypeExample example);

    int updateByExample(@Param("record") ResourceType record, @Param("example") ResourceTypeExample example);

    int updateByPrimaryKeySelective(ResourceType record);

    int updateByPrimaryKey(ResourceType record);
}