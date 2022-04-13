package cn.compose.admin.rabbitmq.consumer;

import cn.compose.admin.rabbitmq.entry.MqDto;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ReceiverMessageConsumer extends DefaultConsumer<MqDto<JSONObject>>{

    private static String REDIS_MSG_RETRY_KEY = "RABBITMQ_MSG_RETRY:";

    @Override
    public void execute(MqDto<JSONObject> jsonObjectMqDto) throws Exception {
        //int sd= 1/0;
        log.info("收到confirm_test_queue队列消息：{}，消息ID:{}", jsonObjectMqDto.getBody().toString(),jsonObjectMqDto.getMessageId());
        //TODO 具体业务
    }


    @Override
    public MqDto getMsgRetry(MqDto<JSONObject> jsonObjectMqDto){
        String msgId = jsonObjectMqDto.getMessageId();
        MqDto mqDto = new MqDto();
        mqDto.setMessageId(msgId);
        int retry = 0;
        Object count = redisTemplate.opsForValue().get(REDIS_MSG_RETRY_KEY+msgId);
        if(count != null){
            retry = Integer.valueOf(count.toString());
        }
        mqDto.setRetry(retry);
        mqDto.setRetrySetting(jsonObjectMqDto.getRetrySetting());
        return mqDto;
    }

    @Override
    public void setMegRetry(MqDto mqDto){
        redisTemplate.opsForValue().set(REDIS_MSG_RETRY_KEY+mqDto.getMessageId(),mqDto.getRetry()+1);
        redisTemplate.expire(REDIS_MSG_RETRY_KEY+mqDto.getMessageId(),60*60, TimeUnit.SECONDS);
    }
}
