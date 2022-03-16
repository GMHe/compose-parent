package cn.compose.admin.api;

import com.contive.plugin.did.DistributedId;
import io.swagger.annotations.Api;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/admin/redis")
@Api(tags = "Redis操作测试接口",value = "Redis操作测试接口")
public class RedisRest {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Resource
    DistributedId distributedId;

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @GetMapping("/getCache")
    public String getCache() {
        redisTemplate.opsForHash().put("test:dd", "1", "value");
        atomicInteger.addAndGet(1);
        System.err.println("==========redis=======" + distributedId.nextId() + "====" + atomicInteger.get());
        return (String) redisTemplate.opsForHash().get("test:dd", "1");
    }
}
