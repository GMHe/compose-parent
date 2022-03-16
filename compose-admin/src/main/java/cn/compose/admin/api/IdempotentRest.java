package cn.compose.admin.api;

import cn.compose.admin.annotations.InterfaceAutoIdempotent;
import cn.compose.admin.base.ApiCodes;
import cn.compose.admin.base.Response;
import cn.compose.admin.base.ResponseBuilder;
import cn.compose.admin.biz.IdempotentBiz;
import cn.compose.admin.entity.Account;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 幂等接口
 */
@RestController
@Slf4j
@RequestMapping("/admin/idempotent")
public class IdempotentRest {
    @Resource
    private IdempotentBiz idempotentBiz;


    /**
     * {
     * "id":11,
     * "version":2,
     * "money":12
     * }
     * <p>
     * version方式update幂等
     *
     * @param account
     * @return
     * @InterfaceAutoIdempotent为接口幂等注解
     */
    @PostMapping("/updateAccount")
    @InterfaceAutoIdempotent
    public Response takeUpdateVersion(@RequestBody Account account) {
        return idempotentBiz.takeUpdateVersion(account);
    }


    @PostMapping("/addAccount")
    @InterfaceAutoIdempotent
    public Response takeAddAccount(@RequestBody Account account) {
        return idempotentBiz.takeAddVersion(account);
    }

}
