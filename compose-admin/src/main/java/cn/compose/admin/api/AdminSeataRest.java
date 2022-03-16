package cn.compose.admin.api;

import cn.compose.admin.base.Response;
import cn.compose.admin.biz.ibiz.IAdminSeataBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName AdminSeataRest
 * @Description 类描述
 * @Author hgm
 * @Date 2021/11/15 14:59
 * @Version 1.0
 **/
@RestController()
@RequestMapping("/admin/compose-admin")
@Slf4j
public class AdminSeataRest {

    @Resource
    private IAdminSeataBiz adminSeataBiz;

    @PostMapping("/account/deduct")
    public Response deduct(@RequestParam("userId") String userId, @RequestParam("money") Long money) {
        return adminSeataBiz.deduct(userId, money);
    }
}
