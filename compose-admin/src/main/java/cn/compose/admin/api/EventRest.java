package cn.compose.admin.api;

import cn.compose.admin.event.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/event")
@Api(tags = "触发事件接口",value = "触发事件接口")
public class EventRest {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(@RequestParam("userName") String username) {
        userService.register(username);
        return "success";
    }

    @GetMapping("/account")
    public String takeAccount(@RequestParam("account") String account) {
        userService.takeAccount(account);
        return "success";
    }

}