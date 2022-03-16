package cn.compose.sync.api;

import cn.compose.sync.base.Response;
import cn.compose.sync.base.ResponseBuilder;
import cn.compose.sync.biz.SyncSeataBiz;
import cn.compose.sync.biz.ibiz.ISyncSeataBiz;
import cn.compose.sync.entity.Storage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName SyncSeataRest
 * @Description 类描述
 * @Author hgm
 * @Date 2021/11/15 14:41
 * @Version 1.0
 **/
@RestController
@RequestMapping("/compose-sync")
public class SyncSeataRest {

    @Resource
    private ISyncSeataBiz syncSeataBiz;

    @PostMapping("/storage/deduct")
    public Response deduct(@RequestParam("id") Long id, @RequestParam("num") Long num){
        return syncSeataBiz.deduct(id,num);
    }


    @PostMapping("/storage/getStorageById")
    public Response getStorageById(@RequestParam("id") Long id){
        return ResponseBuilder.ok(syncSeataBiz.getStorageById(id));
    }

}
