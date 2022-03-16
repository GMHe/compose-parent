package cn.compose.alert.apiFeign;

import cn.compose.alert.base.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @interfaceName AccountFeignRest
 * @Description 接口描述
 * @Author hgm
 * @Date 2021/11/15 15:24
 * @Version 1.0
 **/
@FeignClient(value = "compose-admin")
public interface AccountFeignRest {


    @PostMapping("/compose-admin/account/deduct")
    public Response deduct(@RequestParam("userId") String userId, @RequestParam("money") Long money);
}
