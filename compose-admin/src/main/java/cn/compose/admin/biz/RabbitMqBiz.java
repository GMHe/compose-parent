package cn.compose.admin.biz;

import cn.compose.admin.exception.BizException;
import cn.compose.admin.rabbitmq.ConfirmCallbackService;
import cn.compose.admin.rabbitmq.entry.MqDto;
import cn.compose.admin.rabbitmq.ReturnCallbackService;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class RabbitMqBiz {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ConfirmCallbackService confirmCallbackService;

    @Autowired
    private ReturnCallbackService returnCallbackService;

    /**
     * 发送普通消息
     *
     * @param mqDto
     */
    public void sendMessage(MqDto mqDto) {
        msgSetting(mqDto);

        rabbitTemplate.convertAndSend(mqDto.getExchange(), mqDto.getRoutingKey(), mqDto,
                message -> {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    message.getMessageProperties().setCorrelationId(mqDto.getMessageId());
                    message.getMessageProperties().setMessageId(mqDto.getMessageId());
                    return message;
                },
                new CorrelationData(UUID.randomUUID().toString()));
    }


    /**
     * 发送ttl消息
     *
     * @param mqDto
     */
    public void sendMessageTTl(MqDto mqDto) {
        msgSetting(mqDto);

        rabbitTemplate.convertAndSend(mqDto.getExchange(), mqDto.getRoutingKey(), mqDto, message -> {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    message.getMessageProperties().setExpiration(mqDto.getExpire() + "");
                    message.getMessageProperties().setCorrelationId(mqDto.getMessageId());
                    message.getMessageProperties().setMessageId(mqDto.getMessageId());
                    return message;
                },
                new CorrelationData(UUID.randomUUID().toString()));
    }


    private void msgSetting(MqDto mqDto) {

        if(StringUtils.isEmpty(mqDto.getMessageId())){
            throw new BizException("messageId 不能为空！");
        }

        /**
         * 确保消息发送失败后可以重新返回到队列中
         * 注意：yml需要配置 publisher-returns: true
         */
        rabbitTemplate.setMandatory(true);

        /**
         * 消费者确认收到消息后，手动ack回执回调处理
         */
        rabbitTemplate.setConfirmCallback(confirmCallbackService);

        /**
         * 消息投递到队列失败回调处理
         */
        rabbitTemplate.setReturnCallback(returnCallbackService);
    }

}
