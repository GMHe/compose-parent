package cn.compose.admin.api;

import cn.compose.admin.annotations.AccessLimiterAop;
import cn.compose.admin.base.ApiCodes;
import cn.compose.admin.base.Response;
import cn.compose.admin.base.ResponseBuilder;
import cn.compose.admin.biz.AccessLimiterBiz;
import cn.compose.admin.config.JsonZoneTimeSerialize;
import cn.compose.admin.entity.UserToken;
import cn.compose.admin.utils.DateTimeUtils;
import cn.compose.admin.utils.LocalDateTimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@Slf4j
@RequestMapping("/admin/redisLimit")
@Api(tags = "Redis限流测试接口",value = "Redis限流测试接口")
public class RedisRateLimiterRest {
    @Resource
    private AccessLimiterBiz accessLimiterBiz;

    @Resource
    private JsonZoneTimeSerialize jsonZoneTimeSerialize;

    @ApiOperation(value = "接口调用限流测试limit1", nickname = "接口调用限流测试limit1")
    @GetMapping("/test")
    @AccessLimiterAop(limit = 1, methodKey = "test")
    public Response test() {
        Response response = new Response();
        response.setCode(ApiCodes.SUCCESS);
        try {
            accessLimiterBiz.limitAccessTest();
            response.setMsg("服务调用成功！");
        } catch (Exception e) {
            response.setMsg("服务异常");
            response.setCode(ApiCodes.ERROR);
            log.error("test error", e);
        }
        return response;
    }

    @ApiOperation(value = "接口调用查看时间转换显示", nickname = "接口调用查看时间转换显示")
    @GetMapping("/list")
    @AccessLimiterAop(limit = 5, methodKey = "list")
    public Response listUserToken(@RequestParam("timeZone")String timeZone){
        List<UserToken> userList=accessLimiterBiz.listUserToken();
        if(CollectionUtils.isEmpty(userList)){
            return ResponseBuilder.ok(new ArrayList<>());
        }
        jsonZoneTimeSerialize=new JsonZoneTimeSerialize();
        jsonZoneTimeSerialize.setTimeZone(timeZone);
        userList.forEach(user->{
            user.setCreateDate(LocalDateTimeUtils.dateFromTimeZone(user.getCreateDate(), timeZone));
            //user.setCreateDate(DateTimeUtils.convertUtcDate2Date(user.getCreateDate(), timeZone));
        });
        return ResponseBuilder.ok(userList);
    }
} 