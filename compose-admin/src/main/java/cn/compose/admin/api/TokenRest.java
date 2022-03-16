package cn.compose.admin.api;

import cn.compose.admin.base.Response;
import cn.compose.admin.base.ResponseBuilder;
import cn.compose.admin.convert.UserConvert;
import cn.compose.admin.dto.UserDTO;
import cn.compose.admin.entity.User;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Slf4j
@RequestMapping("/admin/token")
@RestController
@Api(tags = "登录测试接口",value = "登录测试接口")
public class TokenRest {

    @GetMapping("/loginToken")
    public Response login(@RequestParam(value = "uuid") String email) {
        log.info(email);
        JSONObject result = JSONUtil.createObj();
        StpUtil.login(email);
        StpUtil.getTokenSession().set(email, StpUtil.getTokenValue());
        result.set("token", StpUtil.getTokenValue());
        return ResponseBuilder.ok(result, "登录成功！");
    }
}
