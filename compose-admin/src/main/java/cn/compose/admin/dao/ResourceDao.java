package cn.compose.admin.dao;

import cn.compose.admin.dto.ResourceDTO;

/**
 * @interfaceName ResourceDao
 * @Description 接口描述
 * @Author hgm
 * @Date 2021/8/8 14:30
 * @Version 1.0
 **/
public interface ResourceDao {

    ResourceDTO getResourceByResourceId(String resourceId);
}
