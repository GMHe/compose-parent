package cn.compose.sync.biz.ibiz;

import cn.compose.sync.base.Response;
import cn.compose.sync.entity.Storage;

/**
 * @interfaceName ISyncSeataBiz
 * @Description 接口描述
 * @Author hgm
 * @Date 2021/11/16 10:11
 * @Version 1.0
 **/
public interface ISyncSeataBiz {

    Response deduct(Long id, Long num);
    Storage getStorageById(Long id);
}
