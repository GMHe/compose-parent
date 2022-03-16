package cn.compose.metric.dao;

import cn.compose.metric.entity.ProvOrder;
import cn.compose.metric.entity.ProvOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProvOrderMapper {
    long countByExample(ProvOrderExample example);

    int deleteByExample(ProvOrderExample example);

    int deleteByPrimaryKey(@Param("id") Long id, @Param("orderNumber") String orderNumber);

    int insert(ProvOrder record);

    int insertSelective(ProvOrder record);

    List<ProvOrder> selectByExample(ProvOrderExample example);

    ProvOrder selectByPrimaryKey(@Param("id") Long id, @Param("orderNumber") String orderNumber);

    int updateByExampleSelective(@Param("record") ProvOrder record, @Param("example") ProvOrderExample example);

    int updateByExample(@Param("record") ProvOrder record, @Param("example") ProvOrderExample example);

    int updateByPrimaryKeySelective(ProvOrder record);

    int updateByPrimaryKey(ProvOrder record);
}