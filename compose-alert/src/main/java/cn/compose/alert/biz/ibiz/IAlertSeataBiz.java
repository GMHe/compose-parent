package cn.compose.alert.biz.ibiz;

import cn.compose.alert.base.Response;

/**
 * @interfaceName IAlertSeataBiz
 * @Description 接口描述
 * @Author hgm
 * @Date 2021/11/16 10:13
 * @Version 1.0
 **/
public interface IAlertSeataBiz {

    public Response create(String userId, Long storageId, Long num);
}
