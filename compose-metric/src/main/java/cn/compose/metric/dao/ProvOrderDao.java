package cn.compose.metric.dao;

import cn.compose.metric.entity.ProvOrder;
import org.apache.ibatis.annotations.Param;

/**
 * @interfaceName ProvOrderDao
 * @Description 接口描述
 * @Author hgm
 * @Date 2021/8/8 17:00
 * @Version 1.0
 **/
public interface ProvOrderDao {
    public void updateProvOrder(@Param("provOrder") ProvOrder provOrder);
}
