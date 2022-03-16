package cn.compose.metric.api;

import cn.compose.metric.base.Response;
import cn.compose.metric.base.ResponseBuilder;
import cn.compose.metric.biz.OrderBiz;
import cn.compose.metric.vo.OrderVO;
import com.contive.plugin.did.DistributedId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName OrderRest
 * @Description 类描述
 * @Author hgm
 * @Date 2021/8/4 19:14
 * @Version 1.0
 **/
@RestController
@RequestMapping("/open/order")
@Slf4j
public class OrderRest {

    @Resource
    private OrderBiz orderBiz;

    @Resource
    DistributedId distributedId;
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Resource
    RedisTemplate<String,Object> redisTemplate;

    @PostMapping("/createOrder")
    public Response createOrder(@RequestParam(value = "userId",required = false) String userId,
                                @RequestParam(value = "resourceId") String resourceId,
                                @RequestParam(value = "payType")String payType){
        OrderVO orderVo=orderBiz.createOrder(userId,resourceId,payType);
        return ResponseBuilder.ok(orderVo,"创建订单成功");

    }


    @GetMapping("/orderNotify")
    public String orderNotify() {
        log.info("已收到通知！");
        return "success";
    }

    @GetMapping("/orderPayResult")
    public String orderPayResult(@RequestParam("orderNo") String orderNo,
                                 @RequestParam("mchOrderNo") String mchOrderNo,
                                 @RequestParam("platformOrderNo") String platformOrderNo,
                                 @RequestParam("amount") String amount,
                                 @RequestParam("actualPayAmount") BigDecimal actualPayAmount) {
        orderBiz.updateOrder(orderNo,platformOrderNo, actualPayAmount);
        return "订单支付成功!";
    }


    @GetMapping("/distributed")
    public String distributed() {
        long id = distributedId.nextId();
        log.info("生成ID:{}",id);
        return String.valueOf(id);
    }

    @GetMapping("/getCashe")
    public String getCashe() {
        redisTemplate.opsForHash().put("test:dd","1","vlaue");
        atomicInteger.addAndGet(1);
        System.err.println("==========redis======="+distributedId.nextId()+"===="+atomicInteger.get());
        return (String) redisTemplate.opsForHash().get("test:dd","1");
    }

}
