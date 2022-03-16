package cn.compose.alert.api;

import cn.compose.alert.base.Response;
import cn.compose.alert.biz.AlertSeataBiz;
import cn.compose.alert.biz.ibiz.IAlertSeataBiz;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName AlertSeataRest
 * @Description 类描述
 * @Author hgm
 * @Date 2021/11/15 15:35
 * @Version 1.0
 **/
@RestController
@RequestMapping("/compose-alert")
public class AlertSeataRest {
    @Resource
    private IAlertSeataBiz alertSeataBiz;

    @PostMapping("/torder/create")
    public Response create(@RequestParam("userId") String userId,
                           @RequestParam("storageId") Long storageId,
                           @RequestParam("num") Long num){
        Response response=alertSeataBiz.create(userId,storageId,num);
        return response;
    }
}
