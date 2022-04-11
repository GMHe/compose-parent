package cn.compose.admin.api;

import cn.compose.admin.biz.TestBiz;
import cn.compose.admin.constant.Constants;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.contive.plugin.did.DistributedId;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/admin/redis")
@Api(tags = "Redis操作测试接口",value = "Redis操作测试接口")
@Slf4j
public class RedisRest {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Resource
    DistributedId distributedId;

    @Resource
    private TestBiz testBiz;

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @GetMapping("/getCache")
    public String getCache() {
        redisTemplate.opsForHash().put("test:dd", "1", "value");
        atomicInteger.addAndGet(1);
        System.err.println("==========redis=======" + distributedId.nextId() + "====" + atomicInteger.get());
        return (String) redisTemplate.opsForHash().get("test:dd", "1");
    }

    @GetMapping("/setCache")
    public String setCache() {
        //测试key 过期通知  单节点可监听到，集群多节点时，需要全部节点监听
        redisTemplate.opsForValue().set("order_345678", "345678");
        redisTemplate.expire("order_345678",20, TimeUnit.SECONDS);

        Boolean flag = testBiz.existedFilteredDevice("5353","TC2040CB1111");
        log.info("TC2040CB1111编号满足屏蔽条件:[{}]",flag);

        testBiz.configText();

        redisTemplate.convertAndSend(Constants.ORDER_STATE_CHANNEL,
                JSONUtil.parseObj("{\"msg_id\":\"msg_id_123456\",\"chan_msg\":\"管道消息通知\"}"));

        return (String) redisTemplate.opsForValue().get("order_345678");
    }

}
