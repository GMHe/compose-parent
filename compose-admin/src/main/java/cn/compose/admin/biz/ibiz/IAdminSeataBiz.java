package cn.compose.admin.biz.ibiz;

import cn.compose.admin.base.Response;

/**
 * @interfaceName IAdminSeataBiz
 * @Description 接口描述
 * @Author hgm
 * @Date 2021/11/16 10:09
 * @Version 1.0
 **/
public interface IAdminSeataBiz {
    public Response deduct(String userId, Long money);
}
