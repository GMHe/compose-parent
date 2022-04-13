package cn.compose.admin.api;

import cn.compose.admin.biz.RabbitMqBiz;
import cn.compose.admin.rabbitmq.config.RabbitMQConfig;
import cn.compose.admin.rabbitmq.entry.MqDto;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/admin/rabbitMq")
@Api(tags = "RabbitMq操作测试接口",value = "Redis操作测试接口")
@Slf4j
public class RabbitMqRest {

    @Resource
    private RabbitMqBiz rabbitMqBiz;

    private final AtomicInteger atomicInteger = new AtomicInteger(0);


    @GetMapping("/sendMsg")
    public String sendMsg() {

        // 发送普通消息
        JSONObject jsonObject = JSONUtil.createObj();
        jsonObject.set("msg","测试RabbitMq消息，普通消息");
        MqDto<JSONObject> mqDto = new MqDto();
        mqDto.setMessageId(UUID.randomUUID().toString());
        mqDto.setBody(jsonObject);
        mqDto.setRetrySetting(2);
        mqDto.setExchange(RabbitMQConfig.CONFIRM_EXCHANGE);
        mqDto.setRoutingKey("confirm.msg");
        rabbitMqBiz.sendMessage(mqDto);
        //rabbitMqBiz.sendMessage(mqDto);


        MqDto<JSONObject> mqDto1 = new MqDto();
        jsonObject.set("msg","测试RabbitMq消息，TTL消息");
        mqDto1.setBody(jsonObject);
        mqDto1.setExpire(10000L);
        mqDto1.setExchange(RabbitMQConfig.CONFIRM_EXCHANGE);
        mqDto1.setRoutingKey("confirm.ttl");
        mqDto1.setMessageId(UUID.randomUUID().toString());
        rabbitMqBiz.sendMessageTTl(mqDto1);


        return "success";
    }

}
