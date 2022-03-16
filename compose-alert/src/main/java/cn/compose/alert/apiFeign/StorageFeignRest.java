package cn.compose.alert.apiFeign;

import cn.compose.alert.base.Response;
import cn.compose.alert.entity.Storage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @interfaceName StorageFeignRest
 * @Description 接口描述
 * @Author hgm
 * @Date 2021/11/15 15:24
 * @Version 1.0
 **/
@FeignClient("compose-sync")
public interface StorageFeignRest {


    @PostMapping("/compose-sync/storage/deduct")
    public Response deduct(@RequestParam("id") Long id,@RequestParam("num") Long num);

    @PostMapping("/compose-sync/storage/getStorageById")
    public Response<Storage> getStorageById(@RequestParam("id") Long id);

}
