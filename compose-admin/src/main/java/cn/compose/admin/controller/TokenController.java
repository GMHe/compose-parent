package cn.compose.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/admin/token")
public class TokenController {
    @GetMapping("/loginSuccess")
    public String loginSuccess(@RequestParam(value = "uuid") String email) {
        log.info(email);
        JSONObject result = JSONUtil.createObj();
        StpUtil.login(email);
        StpUtil.getTokenSession().set(email, StpUtil.getTokenValue());
        result.set("token", StpUtil.getTokenValue());
        return "forward:/loginSuccess";
    }
}
