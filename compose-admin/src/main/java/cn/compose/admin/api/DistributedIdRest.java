package cn.compose.admin.api;

import com.contive.plugin.did.DistributedId;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/admin/id")
@Slf4j
@RestController
@Api(tags = "分布式ID接口",value = "分布式ID接口")
public class DistributedIdRest {
    @Resource
    DistributedId distributedId;

    @GetMapping("/distributed")
    public String distributed() {
        long id = distributedId.nextId();
        log.info("生成ID:{}", id);
        return String.valueOf(id);
    }
}
